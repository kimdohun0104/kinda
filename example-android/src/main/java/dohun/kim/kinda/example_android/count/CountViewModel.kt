package dohun.kim.kinda.example_android.count

import dohun.kim.kinda.kinda_android.KindaViewModel

class CountViewModel : KindaViewModel<CountState, CountEvent, CountSideEffect>(
    initialState = CountState(),
    reducer = CountReducer(),
    sideEffectHandler = CountSideEffectHandler()
)