package com.dohun.kinda.core

class KindaStateMachine<S : com.dohun.kinda.core.concept.KindaState, E : com.dohun.kinda.core.concept.KindaEvent, SE : com.dohun.kinda.core.concept.KindaSideEffect> internal constructor(
    val initialState: S,
    private val nexts: MutableMap<KindaMatcher<E, E>, (S, E) -> Next<S, SE>>,
    private val suspends: MutableMap<KindaMatcher<SE, SE>, suspend (KindaOutput.Valid<S, E, SE>) -> Any?>
) {

    fun reduce(state: S, event: E): KindaOutput<S, E, SE> {
        return state.toOutput(event)
    }

    fun suspendOrNull(sideEffect: SE?): (suspend (KindaOutput.Valid<S, E, SE>) -> Any?)? {
        sideEffect?.let {
            return suspends.filter { it.key.match(sideEffect) }
                .map { it.value }
                .firstOrNull()
        }
        return null
    }

    private fun S.toOutput(event: E): KindaOutput<S, E, SE> {
        nexts.forEach {
            if (it.key.match(event)) {
                val (next, sideEffect) = it.value(this, event)
                return KindaOutput.Valid(this, event, next, sideEffect)
            }
        }
        return KindaOutput.Void(this, event)
    }

    data class Next<STATE : com.dohun.kinda.core.concept.KindaState, SIDE_EFFECT : com.dohun.kinda.core.concept.KindaSideEffect> internal constructor(
        val toState: STATE,
        val sideEffect: SIDE_EFFECT?
    )
}

fun <S : com.dohun.kinda.core.concept.KindaState, E : com.dohun.kinda.core.concept.KindaEvent, SE : com.dohun.kinda.core.concept.KindaSideEffect> buildStateMachine(
    initialState: S,
    init: KindaStateMachineBuilder<S, E, SE>.() -> Unit
) = KindaStateMachineBuilder<S, E, SE>(initialState).apply(init).build()