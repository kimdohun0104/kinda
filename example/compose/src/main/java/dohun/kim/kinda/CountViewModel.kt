package dohun.kim.kinda

import android.util.Log
import dohun.kim.kinda.kinda_android.KindaViewModel
import dohun.kim.kinda.kinda_dsl.buildReducer
import dohun.kim.kinda.kinda_dsl.buildSideEffectHandler

class CountViewModel : KindaViewModel<CountState, CountEvent, CountSideEffect>(
    initialState = CountState(),
    reducer = buildReducer {
        whenEvent<CountEvent.Increase> {
            next(copy(count = count + 1))
        }
    },
    sideEffectHandler = buildSideEffectHandler {

    }
)