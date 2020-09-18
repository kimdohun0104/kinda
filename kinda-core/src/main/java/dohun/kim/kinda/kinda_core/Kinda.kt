package dohun.kim.kinda.kinda_core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Kinda<S : KindaState, E : KindaEvent, SE : KindaSideEffect> private constructor(
    initialState: S,
    private val reducer: KindaReducer<S, E, SE>,
    private val sideEffectHandler: KindaSideEffectHandler<S, E, SE>,
    private val render: (state: S) -> Unit,
    private val coroutineScope: CoroutineScope
) {
    private var state: S = initialState

    fun intent(event: E) {
        val next = reducer.reduce(state, event)

        next.state?.let { state ->
            this.state = state
            render.invoke(state)
        }

        next.sideEffect?.let { sideEffect ->
            coroutineScope.launch {
                val result = sideEffectHandler.handle(state, sideEffect)

                intent(result)
            }
        }
    }

    data class Builder<S : KindaState, E : KindaEvent, SE : KindaSideEffect>(
        private var initialState: S? = null,
        private var reducer: KindaReducer<S, E, SE>? = null,
        private var sideEffectHandler: KindaSideEffectHandler<S, E, SE>? = null,
        private var render: ((state: S) -> Unit)? = null,
        private var coroutineScope: CoroutineScope? = null
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

        fun build(): Kinda<S, E, SE> {
            checkNotNull(initialState)
            checkNotNull(reducer)
            checkNotNull(sideEffectHandler)
            checkNotNull(render)
            checkNotNull(coroutineScope)

            return Kinda(
                initialState = initialState!!,
                coroutineScope = coroutineScope!!,
                reducer = reducer!!,
                render = render!!,
                sideEffectHandler = sideEffectHandler!!
            )
        }
    }
}
