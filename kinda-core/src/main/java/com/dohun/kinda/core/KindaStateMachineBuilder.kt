package com.dohun.kinda.core

class KindaStateMachineBuilder<STATE : KindaState, EVENT : KindaEvent, SIDE_EFFECT : KindaSideEffect>(
    private val initialState: STATE
) {
    val nexts =
        mutableMapOf<KindaKey<EVENT, EVENT>, (STATE, EVENT) -> KindaStateMachine.Next<STATE, SIDE_EFFECT>>()

    val ioTasks =
        mutableMapOf<KindaKey<SIDE_EFFECT, SIDE_EFFECT>, suspend (KindaOutput.Valid<STATE, EVENT, SIDE_EFFECT>) -> Any?>()

    inline fun <reified E : EVENT> whenEvent(
        noinline next: STATE.(E) -> KindaStateMachine.Next<STATE, SIDE_EFFECT>
    ) {
        nexts[KindaKey<EVENT, E>(E::class.java)] = { state, event ->
            next(state, event as E)
        }
    }

    inline fun <reified SE : SIDE_EFFECT> whenIoTask(
        noinline ioTask: suspend (KindaOutput<STATE, EVENT, SE>) -> Any?
    ) {
        ioTasks[KindaKey<SIDE_EFFECT, SE>(SE::class.java)] =
            ioTask as suspend (KindaOutput.Valid<STATE, EVENT, SIDE_EFFECT>) -> Any?
    }

    fun STATE.next(state: STATE, sideEffect: SIDE_EFFECT? = null) = KindaStateMachine.Next(
        state, sideEffect
    )

    fun STATE.dispatch(sideEffect: SIDE_EFFECT) = KindaStateMachine.Next(
        this, sideEffect
    )

    fun build() =
        KindaStateMachine(initialState, nexts, ioTasks)
}