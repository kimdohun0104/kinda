package com.dohun.kinda.core

class KindaStateMachineBuilder<STATE : com.dohun.kinda.core.concept.KindaState, EVENT : com.dohun.kinda.core.concept.KindaEvent, SIDE_EFFECT : com.dohun.kinda.core.concept.KindaSideEffect>(
    private val initialState: STATE
) {
    val nexts = mutableMapOf<KindaMatcher<EVENT, EVENT>, (STATE, EVENT) -> com.dohun.kinda.core.KindaStateMachine.Next<STATE, SIDE_EFFECT>>()

    val suspends = mutableMapOf<KindaMatcher<SIDE_EFFECT, SIDE_EFFECT>, suspend (KindaOutput.Valid<STATE, EVENT, SIDE_EFFECT>) -> Any?>()

    inline fun <reified E : EVENT> whenEvent(
        noinline next: STATE.(E) -> com.dohun.kinda.core.KindaStateMachine.Next<STATE, SIDE_EFFECT>
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
        com.dohun.kinda.core.KindaStateMachine.Next(state, sideEffect)

    fun STATE.dispatch(sideEffect: SIDE_EFFECT) =
        com.dohun.kinda.core.KindaStateMachine.Next(this, sideEffect)

    fun STATE.noChange() =
        com.dohun.kinda.core.KindaStateMachine.Next(this, null as SIDE_EFFECT?)

    fun build() = com.dohun.kinda.core.KindaStateMachine(initialState, nexts, suspends)
}