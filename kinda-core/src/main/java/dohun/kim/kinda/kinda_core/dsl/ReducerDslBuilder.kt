package dohun.kim.kinda.kinda_core.dsl

import dohun.kim.kinda.kinda_core.*

class ReducerDslBuilder<S : KindaState, E : KindaEvent, SE : KindaSideEffect> {

    val reduceMap = HashMap<Class<E>, S.(E) -> Next<S, SE>>()

    inline fun <reified EVENT: E>whenEvent(
        noinline next: S.(EVENT) -> Next<S, SE>
    ) {
        reduceMap[EVENT::class.java as Class<E>] = next as S.(E) -> Next<S, SE>
    }

    fun next(state: S, sideEffect: SE? = null) = Next(state, sideEffect)

    fun dispatch(sideEffect: SE) = Next<S, SE>(null, sideEffect)

    fun noChange() = Next<S, SE>(null, null)

    fun build() = object : KindaReducer<S, E, SE> {
        override fun reduce(state: S, event: E): Next<S, SE> {
            reduceMap.keys.forEach { key ->
                if (key.isInstance(event)) {
                    return reduceMap[key]!!.invoke(state, event)
                }
            }
            throw IllegalStateException("Event not handled ${event::class}")
        }
    }
}

fun <S : KindaState, E : KindaEvent, SE : KindaSideEffect> buildReducer(
    init: ReducerDslBuilder<S, E, SE>.() -> Unit
) = ReducerDslBuilder<S, E, SE>().apply(init).build()