package dohun.kim.kinda.kinda_dsl

import dohun.kim.kinda.kinda_core.*

class ReducerDslBuilder<S : KindaState, E : KindaEvent, SE : KindaSideEffect> {

    val reduceMap = HashMap<E, S.(E) -> Next<S, SE>>()

    fun whenEvent(
        event: E,
        next: S.(E) -> Next<S, SE>
    ) {
        reduceMap[event] = next
    }

    fun next(state: S, sideEffect: SE? = null) = Next(state, sideEffect)

    fun dispatch(sideEffect: SE) = Next<S, SE>(null, sideEffect)

    fun noChange() = Next<S, SE>(null, null)

    fun build() = object : KindaReducer<S, E, SE> {
        override fun reduce(state: S, event: E): Next<S, SE> {
            reduceMap.keys.forEach { key ->
                if (key::class.isInstance(event)) {
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