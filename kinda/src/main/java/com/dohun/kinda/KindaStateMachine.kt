package com.dohun.kinda

import android.util.Log
import com.dohun.kinda.KindaStateMachine.KindaStateMachineBuilder
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

class KindaStateMachine<S : KindaState, E : KindaEvent, SE : KindaSideEffect> internal constructor(
    val initialState: S,
    private val nexts: MutableMap<KindaKey<E, E>, (S, E) -> Next<S, SE>>,
    private val effects: MutableMap<KindaKey<SE, SE>, (KindaOutput.Valid<S, E, SE>) -> Flow<Any?>>
) {

    fun reduce(state: S, event: E): KindaOutput<S, E, SE> {
        return state.toOutput(event)
    }

    fun effect(sideEffect: SE?): ((KindaOutput.Valid<S, E, SE>) -> Flow<Any?>)? {
        sideEffect?.let {
            return effects.filter { it.key.check(sideEffect) }
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

    class KindaStateMachineBuilder<STATE : KindaState, EVENT : KindaEvent, SIDE_EFFECT : KindaSideEffect>(
        private val initialState: STATE
    ) {
        val nexts =
            mutableMapOf<KindaKey<EVENT, EVENT>, (STATE, EVENT) -> Next<STATE, SIDE_EFFECT>>()

        val effects =
            mutableMapOf<KindaKey<SIDE_EFFECT, SIDE_EFFECT>, (KindaOutput.Valid<STATE, EVENT, SIDE_EFFECT>) -> Flow<Any?>>()

        inline fun <reified E : EVENT> whenEvent(
            noinline next: STATE.(EVENT) -> Next<STATE, SIDE_EFFECT>
        ) {
            nexts[KindaKey<EVENT, E>(E::class.java)] = { state, event ->
                next(state, event)
            }
        }

        inline fun <reified SE : SIDE_EFFECT> whenEffect(
            noinline init: (KindaOutput<STATE, EVENT, SE>) -> Flow<Any?>
        ) {
            effects[KindaKey<SIDE_EFFECT, SE>(SE::class.java)] =
                init as (KindaOutput.Valid<STATE, EVENT, SIDE_EFFECT>) -> Flow<Any?>
        }


        fun STATE.next(state: STATE, sideEffect: SIDE_EFFECT? = null) = Next(
            state, sideEffect
        )

        fun build(): KindaStateMachine<STATE, EVENT, SIDE_EFFECT> {
            Log.d("DEBUGLOG", initialState.toString())
            return KindaStateMachine(initialState, nexts, effects)
        }
    }
}


fun <S : KindaState, E : KindaEvent, SE : KindaSideEffect> buildKindaStateMachine(
    initialState: S,
    init: KindaStateMachineBuilder<S, E, SE>.() -> Unit
) = KindaStateMachineBuilder<S, E, SE>(initialState).apply(init).build()