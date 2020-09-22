package dohun.kim.kinda.androidwithdsl

import dohun.kim.kinda.kinda_android.KindaViewModel
import dohun.kim.kinda.kinda_core.interceptor.LoggingInterceptor
import dohun.kim.kinda.kinda_dsl.buildReducer
import dohun.kim.kinda.kinda_dsl.buildSideEffectHandler
import kotlinx.coroutines.delay

class CountViewModel : KindaViewModel<CountState, CountEvent, CountSideEffect>(
    initialState = CountState(),
    reducer = buildReducer {
        whenEvent(CountEvent.AttemptMagic) {
            dispatch(CountSideEffect.Magic)
        }

        whenEvent(CountEvent.Decrease) {
            next(copy(count = count - 1))
        }

        whenEvent(CountEvent.Increase) {
            next(copy(count = count + 1))
        }

        whenEvent(CountEvent.Increase1000) {
            next(copy(count = count + 1000))
        }
    },
    sideEffectHandler = buildSideEffectHandler {
        whenSideEffect(CountSideEffect.Magic) {
            delay(1000)
            CountEvent.Increase1000
        }
    },
    interceptors = setOf(LoggingInterceptor())
)