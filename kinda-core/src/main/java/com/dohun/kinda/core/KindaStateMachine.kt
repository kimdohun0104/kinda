package com.dohun.kinda.core

class KindaStateMachine<S : KindaState, E : KindaEvent, SE : KindaSideEffect> internal constructor(
    val initialState: S,
    private val nexts: MutableMap<KindaKey<E, E>, (S, E) -> Next<S, SE>>,
    private val ioTasks: MutableMap<KindaKey<SE, SE>, suspend (KindaOutput.Valid<S, E, SE>) -> Any?>
) {

    fun reduce(state: S, event: E): KindaOutput<S, E, SE> {
        return state.toOutput(event)
    }

    fun ioTaskOrNull(sideEffect: SE?): (suspend (KindaOutput.Valid<S, E, SE>) -> Any?)? {
        sideEffect?.let {
            return ioTasks.filter { it.key.check(sideEffect) }
                .map { it.value }
                .firstOrNull()
        }
        return null
    }

    private fun S.toOutput(event: E): KindaOutput<S, E, SE> {
        nexts.forEach {
            if (it.key.check(event)) {
                val (next, sideEffect) = it.value(this, event)
                return KindaOutput.Valid(this, event, next, sideEffect)
            }
        }
        return KindaOutput.Void(this, event)
    }

    data class Next<STATE : KindaState, SIDE_EFFECT : KindaSideEffect> internal constructor(
        val toState: STATE,
        val sideEffect: SIDE_EFFECT?
    )
}

fun <S : KindaState, E : KindaEvent, SE : KindaSideEffect> buildStateMachine(
    initialState: S,
    init: KindaStateMachineBuilder<S, E, SE>.() -> Unit
) = KindaStateMachineBuilder<S, E, SE>(initialState).apply(init).build()