package com.dohun.kinda.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dohun.kinda.core.KindaOutput
import com.dohun.kinda.core.KindaStateMachine
import com.dohun.kinda.core.concept.KindaEvent
import com.dohun.kinda.core.concept.KindaSideEffect
import com.dohun.kinda.core.concept.KindaState
import com.dohun.kinda.core.concept.KindaViewEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

abstract class KindaViewModel<S : KindaState, E : KindaEvent, SE : KindaSideEffect, VE : KindaViewEffect> : ViewModel() {

    abstract val stateMachine: KindaStateMachine<S, E, SE>

    private var state: S = stateMachine.initialState

    private val _currentState = MutableLiveData<S>()
    val currentState: LiveData<S> = _currentState

    private val _viewEffect = KindaSingleLiveEvent<VE>()
    val viewEffect: KindaSingleLiveEvent<VE> = _viewEffect

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        _currentState.value = state
    }

    fun viewEffect(viewEffect: VE) {
        _viewEffect.value = viewEffect
    }

    fun intent(event: E) {
        KindaLogger.log(event)
        handleOutput(stateMachine.reduce(state, event))
    }

    private fun handleOutput(output: KindaOutput<S, E, SE>) {
        when (output) {
            is KindaOutput.Valid -> {
                KindaLogger.log(output.from, output.next)
                state = output.next
                renderNewState(state)
                when (output.sideEffect) {
                    null -> return
                    else -> startSideEffectCycle(output)
                }
            }
            else -> Unit
        }
    }

    private fun startSideEffectCycle(output: KindaOutput.Valid<S, E, SE>) {
        stateMachine.suspendOrNull(output.sideEffect)?.let { sideEffect ->
            uiScope.launch {
                KindaLogger.log(output.sideEffect!!)
                handleSideEffectCycleResult(sideEffect(output))
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleSideEffectCycleResult(result: Any?) {
        when (result) {
            is KindaState -> {
                state = result as S
                renderNewState(state)
            }
            is KindaEvent -> {
                intent(result as E)
            }
        }
    }

    private fun renderNewState(newState: S) {
        _currentState.value = newState
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}