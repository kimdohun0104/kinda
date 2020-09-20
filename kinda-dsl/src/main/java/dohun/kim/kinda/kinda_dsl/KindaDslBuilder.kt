package dohun.kim.kinda.kinda_dsl

import dohun.kim.kinda.kinda_core.*
import kotlinx.coroutines.CoroutineScope

class KindaDslBuilder<S : KindaState, E : KindaEvent, SE : KindaSideEffect>(
    private val initialState: S,
    private val coroutineScope: CoroutineScope,
    private val render: (S) -> Unit
) {

    val reduceMap = HashMap<E, S.(E) -> Next<S, SE>>()
    val sideEffectHandleMap = HashMap<SE, suspend S.() -> E>()

    fun whenEvent(
        event: E,
        next: S.(E) -> Next<S, SE>
    ) {
        reduceMap[event] = next
    }

    fun whenSideEffect(
        sideEffect: SE,
        next: suspend S.() -> E
    ) {
        sideEffectHandleMap[sideEffect] = next
    }

    fun next(state: S, sideEffect: SE? = null) = Next(state, sideEffect)

    fun dispatch(sideEffect: SE) = Next(null, sideEffect)

    fun noChange() = Next(null, null)

    fun build(): Kinda<S, E, SE> {
        return Kinda.Builder<S, E, SE>()
            .initialState(initialState)
            .coroutineScope(coroutineScope)
            .render(render)
            .reducer(object : KindaReducer<S, E, SE> {
                override fun reduce(state: S, event: E): Next<S, SE> {
                    reduceMap.keys.forEach { key ->
                        if (key::class.isInstance(state)) {
                            return reduceMap[key]!!.invoke(state, event)
                        }
                    }
                    throw IllegalStateException("Event not handled ${event::class}")
                }
            })
            .sideEffectHandler(object : KindaSideEffectHandler<S, E, SE> {
                override suspend fun handle(state: S, sideEffect: SE): E {
                    sideEffectHandleMap.keys.forEach { key ->
                        if (key::class.isInstance(sideEffect)) {
                            return sideEffectHandleMap[key]!!.invoke(state)
                        }
                    }
                    throw IllegalStateException("SideEffect not handled ${sideEffect::class}")
                }
            })
            .build()
    }
}

fun <S : KindaState, E : KindaEvent, SE : KindaSideEffect> buildKinda(
    initialState: S,
    coroutineScope: CoroutineScope,
    render: (S) -> Unit,
    init: KindaDslBuilder<S, E, SE>.() -> Unit
): Kinda<S, E, SE> {
    return KindaDslBuilder<S, E, SE>(initialState, coroutineScope, render).apply(init).build()
}