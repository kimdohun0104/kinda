package com.dohun.kinda.core.stateMachine

import com.dohun.kinda.core.KindaMatcher
import com.dohun.kinda.core.KindaOutput
import com.dohun.kinda.core.concept.KindaEvent
import com.dohun.kinda.core.concept.KindaSideEffect
import com.dohun.kinda.core.concept.KindaState

class KindaStateMachineBuilder<STATE : KindaState, EVENT : KindaEvent, SIDE_EFFECT : KindaSideEffect>(
    private val initialState: STATE
) {
    val nexts = mutableMapOf<KindaMatcher<EVENT, EVENT>, (STATE, EVENT) -> KindaStateMachine.Next<STATE, SIDE_EFFECT>>()

    val suspends = mutableMapOf<KindaMatcher<SIDE_EFFECT, SIDE_EFFECT>, suspend (KindaOutput.Valid<STATE, EVENT, SIDE_EFFECT>) -> Any?>()

    inline fun <reified E : EVENT> whenEvent(
        noinline next: STATE.(E) -> KindaStateMachine.Next<STATE, SIDE_EFFECT>
    ) {
        nexts[KindaMatcher(E::class.java) as KindaMatcher<EVENT, EVENT>] = { state, event ->
            next(state, event as E)
        }
    }

    inline fun <reified SE : SIDE_EFFECT> whenSideEffect(
        noinline ioTask: suspend (KindaOutput<STATE, EVENT, SE>) -> Any?
    ) {
        suspends[KindaMatcher(SE::class.java) as KindaMatcher<SIDE_EFFECT, SIDE_EFFECT>] =
            ioTask as suspend (KindaOutput.Valid<STATE, EVENT, SIDE_EFFECT>) -> Any?
    }

    fun STATE.next(state: STATE, sideEffect: SIDE_EFFECT? = null) =
        KindaStateMachine.Next(state, sideEffect)

    fun STATE.dispatch(sideEffect: SIDE_EFFECT) =
        KindaStateMachine.Next(this, sideEffect)

    fun STATE.noChange() =
        KindaStateMachine.Next(this, null as SIDE_EFFECT?)

    fun build() = KindaStateMachine(initialState, nexts, suspends)
}