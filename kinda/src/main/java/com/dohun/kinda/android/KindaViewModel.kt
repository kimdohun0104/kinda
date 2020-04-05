package com.dohun.kinda.android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dohun.kinda.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class KindaViewModel<S : KindaState, E : KindaEvent, SE : KindaSideEffect> : ViewModel() {

    abstract val stateMachine: KindaStateMachine<S, E, SE>

    private var state: S = stateMachine.initialState

    val currentState = MutableLiveData<S>()

    init {
        currentState.value = state
    }

    fun intent(event: KindaEvent) {
        model(event as E)
    }

    private fun model(event: E): KindaOutput<S, E, SE> {
        return stateMachine.reduce(state, event).also {
            handleOutput(it)
        }
    }

    private fun handleOutput(output: KindaOutput<S, E, SE>) {
        when (output) {
            is KindaOutput.Valid -> {
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
        stateMachine.effect(output.sideEffect)?.let {
            viewModelScope.launch(Dispatchers.IO) {
                it(output).collect { handleResult(it) }
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
        currentState.value = newState
    }
}