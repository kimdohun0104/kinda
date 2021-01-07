package dohun.kim.kinda.kinda_core

import dohun.kim.kinda.kinda_core.interceptor.*
import dohun.kim.kinda.kinda_core.logging.kindaLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Kinda<S : KindaState, E : KindaEvent, SE : KindaSideEffect> private constructor(
    val initialState: S,
    private val reducer: KindaReducer<S, E, SE>,
    private val sideEffectHandler: KindaSideEffectHandler<S, E, SE>?,
    private val render: (state: S) -> Unit,
    private val coroutineScope: CoroutineScope,
    private val interceptors: Set<Interceptor<S, E, SE>>
) {
    private var state: S = initialState

    fun intent(event: E) {
        interceptors.invokeBeforeReduce(state, event)
        kindaLogger?.beforeReduce(state, event)

        val next = reducer.reduce(state, event)

        kindaLogger?.afterReduce(next.state)
        interceptors.invokeAfterReduce(next.state, event)

        next.state?.let { state ->
            this.state = state
            render.invoke(state)
        }

        next.sideEffect?.let { sideEffect ->
            coroutineScope.launch(Dispatchers.IO) {

                interceptors.invokeBeforeHandleSideEffect(state, sideEffect)
                kindaLogger?.beforeHandleSideEffect(sideEffect)

                sideEffectHandler?.handle(sideEffect)?.let { sideEffectResult ->
                    interceptors.invokeAfterHandleSideEffect(state, sideEffectResult, sideEffect)
                    kindaLogger?.afterHandleSideEffect(sideEffectResult, sideEffect)

                    intent(sideEffectResult)
                }
            }
        }
    }

    data class Builder<S : KindaState, E : KindaEvent, SE : KindaSideEffect>(
        private var initialState: S? = null,
        private var reducer: KindaReducer<S, E, SE>? = null,
        private var sideEffectHandler: KindaSideEffectHandler<S, E, SE>? = null,
        private var render: ((state: S) -> Unit)? = null,
        private var coroutineScope: CoroutineScope? = null,
        private var interceptors: HashSet<Interceptor<S, E, SE>> = HashSet()
    ) {
        fun initialState(initialState: S) =
            apply { this.initialState = initialState }

        fun reducer(reducer: KindaReducer<S, E, SE>) =
            apply { this.reducer = reducer }

        fun sideEffectHandler(sideEffectHandler: KindaSideEffectHandler<S, E, SE>) =
            apply { this.sideEffectHandler = sideEffectHandler }

        fun render(render: (state: S) -> Unit) =
            apply { this.render = render }

        fun coroutineScope(coroutineScope: CoroutineScope) =
            apply { this.coroutineScope = coroutineScope }

        @Deprecated("Interceptor deprecated since 1.3.0")
        fun addInterceptor(interceptor: Interceptor<S, E, SE>) =
            apply { this.interceptors.add(interceptor) }

        @Deprecated("Interceptor deprecated since 1.3.0")
        fun removeInterceptor(interceptor: Interceptor<S, E, SE>) =
            apply { this.interceptors.remove(interceptor) }

        @Deprecated("Interceptor deprecated since 1.3.0")
        fun addInterceptors(interceptors: Set<Interceptor<S, E, SE>>) =
            apply { this.interceptors.addAll(interceptors) }

        fun build(): Kinda<S, E, SE> {
            checkNotNull(initialState)
            checkNotNull(reducer)
            checkNotNull(render)

            return Kinda(
                initialState = initialState!!,
                reducer = reducer!!,
                render = render!!,
                sideEffectHandler = sideEffectHandler,
                coroutineScope = coroutineScope ?: GlobalScope,
                interceptors = interceptors
            )
        }
    }
}
