package dohun.kim.kinda.kinda_android

import androidx.lifecycle.ViewModel
import dohun.kim.kinda.kinda_core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class KindaViewModel<S : KindaState, E : KindaEvent, SE : KindaSideEffect>(
    private val initialState: S
) : ViewModel() {

    abstract val reducer: KindaReducer<S, E, SE>
    abstract val sideEffectHandler: KindaSideEffectHandler<S, E, SE>

    private val viewModelJob = SupervisorJob()
    protected val kindaScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val kinda: Kinda<S, E, SE> by lazy {
        Kinda.Builder<S, E, SE>()
            .coroutineScope(kindaScope)
            .initialState(initialState)
            .reducer(reducer)
            .sideEffectHandler(sideEffectHandler)
            .render { state -> _state.value = state  }
            .build()
    }

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun intent(event: E) {
        kinda.intent(event)
    }
}