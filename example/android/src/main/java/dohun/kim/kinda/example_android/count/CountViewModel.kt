package dohun.kim.kinda.example_android.count

import dohun.kim.kinda.kinda_android.KindaViewModel
import dohun.kim.kinda.kinda_core.KindaReducer
import dohun.kim.kinda.kinda_core.KindaSideEffectHandler

class CountViewModel : KindaViewModel<CountState, CountEvent, CountSideEffect>(
    initialState = CountState()
) {
    override val reducer: KindaReducer<CountState, CountEvent, CountSideEffect>
        get() = CountReducer()

    override val sideEffectHandler: KindaSideEffectHandler<CountState, CountEvent, CountSideEffect>
        get() = CountSideEffectHandler()
}