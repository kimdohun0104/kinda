package dohun.kim.kinda.kinda_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dohun.kim.kinda.kinda_core.*
import dohun.kim.kinda.kinda_core.interceptor.Interceptor

abstract class KindaViewModel<S : KindaState, E : KindaEvent, SE : KindaSideEffect>(
    private val initialState: S,
    reducer: KindaReducer<S, E, SE>,
    sideEffectHandler: KindaSideEffectHandler<S, E, SE>,
    interceptors: Set<Interceptor<S, E, SE>> = emptySet()
) : ViewModel() {

    val stateClass: Class<out S>
        get() = initialState::class.java

    private val kinda: Kinda<S, E, SE> = Kinda.Builder<S, E, SE>()
        .coroutineScope(viewModelScope)
        .initialState(initialState)
        .reducer(reducer)
        .sideEffectHandler(sideEffectHandler)
        .render { state -> _stateLiveData.postValue(state) }
        .addInterceptors(interceptors)
        .build()

    private val _stateLiveData = MutableLiveData<S>().apply { value = kinda.initialState }
    val stateLiveData: LiveData<S> = _stateLiveData

    fun intent(event: E) {
        kinda.intent(event)
    }
}