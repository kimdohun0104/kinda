package com.dohun.kinda.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dohun.kinda.core.*
import kotlinx.coroutines.launch

abstract class KindaViewModel<S : KindaState, E : KindaEvent, SE : KindaSideEffect> : ViewModel() {

    abstract val stateMachine: KindaStateMachine<S, E, SE>

    private var state: S = stateMachine.initialState

    private val _currentState = MutableLiveData<S>()
    val currentState: LiveData<S> = _currentState

    init {
        _currentState.value = state
    }

    fun intent(event: E) {
        KindaLogger.log(event)
        stateMachine.reduce(state, event).also {
            handleOutput(it)
        }
    }

    private fun handleOutput(output: KindaOutput<S, E, SE>) {
        when (output) {
            is KindaOutput.Valid -> {
                KindaLogger.log(output.from, output.next)
                state = output.next
                view(state)
                when (output.sideEffect) {
                    null -> return
                    else -> handleSideEffect(output)
                }
            }
            else -> Unit
        }
    }

    private fun handleSideEffect(output: KindaOutput.Valid<S, E, SE>) {
        stateMachine.suspendOrNull(output.sideEffect)?.let { suspendFunction ->
            viewModelScope.launch {
                KindaLogger.log(output.sideEffect!!)
                handleResult(suspendFunction(output))
            }
        }
    }

    private fun handleResult(result: Any?) {
        when (result) {
            is KindaState -> {
                state = result as S
                view(state)
            }
            is KindaEvent -> {
                intent(result as E)
            }
        }
    }

    private fun view(newState: S) {
        _currentState.value = newState
    }
}