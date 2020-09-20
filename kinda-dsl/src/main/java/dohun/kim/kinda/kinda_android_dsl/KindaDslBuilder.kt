package dohun.kim.kinda.kinda_android_dsl

import dohun.kim.kinda.kinda_core.*
import kotlinx.coroutines.CoroutineScope

class KindaDslBuilder<S : KindaState, E : KindaEvent, SE : KindaSideEffect>(
    private val initialState: S,
    private val coroutineScope: CoroutineScope,
    private val render: (S) -> Unit
) {

    val reduceMap = HashMap<Class<E>, S.(E) -> Next<S, SE>>()
    val sideEffectHandleMap = HashMap<Class<SE>, suspend S.() -> E>()

    inline fun <reified EVENT : E> whenEvent(
        noinline next: S.(EVENT) -> Next<S, SE>
    ) {
        reduceMap[EVENT::class.java as Class<E>] = next as S.(E) -> Next<S, SE>
    }

    inline fun <reified SIDE_EFFECT : SE> whenSideEffect(
        noinline next: suspend S.() -> E
    ) {
        sideEffectHandleMap[SIDE_EFFECT::class.java as Class<SE>] = next
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
                        if (key.isInstance(state)) {
                            return reduceMap[key]!!.invoke(state, event)
                        }
                    }
                    throw IllegalStateException("Event not handled")
                }
            })
            .sideEffectHandler(object : KindaSideEffectHandler<S, E, SE> {
                override suspend fun handle(state: S, sideEffect: SE): E {
                    sideEffectHandleMap.keys.forEach { key ->
                        if (key.isInstance(sideEffect)) {
                            return sideEffectHandleMap[key]!!.invoke(state)
                        }
                    }
                    throw IllegalStateException("SideEffect not handled")
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