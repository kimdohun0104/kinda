package com.dohun.kinda.core

import com.dohun.kinda.core.concept.KindaEvent
import com.dohun.kinda.core.concept.KindaSideEffect
import com.dohun.kinda.core.concept.KindaState

class KindaStateMachine<S : KindaState, E : KindaEvent, SE : KindaSideEffect> internal constructor(
    val initialState: S,
    private val nexts: MutableMap<KindaMatcher<E, E>, (S, E) -> Next<S, SE>>,
    private val sideEffects: MutableMap<KindaMatcher<SE, SE>, suspend (KindaOutput.Valid<S, E, SE>) -> Any?>
) {

    internal fun reduce(state: S, event: E): KindaOutput<S, E, SE> {
        return state.toOutput(event)
    }

    internal fun suspendOrNull(sideEffect: SE?): (suspend (KindaOutput.Valid<S, E, SE>) -> Any?)? {
        sideEffect?.let {
            return sideEffects.filter { it.key.match(sideEffect) }
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

    data class Next<STATE : KindaState, SIDE_EFFECT : KindaSideEffect> internal constructor(
        val toState: STATE,
        val sideEffect: SIDE_EFFECT?
    )
}

fun <S : KindaState, E : KindaEvent, SE : KindaSideEffect> buildStateMachine(
    initialState: S,
    init: KindaStateMachineBuilder<S, E, SE>.() -> Unit
) = KindaStateMachineBuilder<S, E, SE>(initialState).apply(init).build()