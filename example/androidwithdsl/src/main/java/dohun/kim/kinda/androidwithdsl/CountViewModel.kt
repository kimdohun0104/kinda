package dohun.kim.kinda.androidwithdsl

import dohun.kim.kinda.kinda_android.KindaViewModel
import dohun.kim.kinda.kinda_core.KindaReducer
import dohun.kim.kinda.kinda_core.KindaSideEffectHandler
import dohun.kim.kinda.kinda_core.dsl.buildReducer
import dohun.kim.kinda.kinda_core.dsl.buildSideEffectHandler
import kotlinx.coroutines.delay

class CountViewModel : KindaViewModel<CountState, CountEvent, CountSideEffect>(
    initialState = CountState()
) {

    override val reducer: KindaReducer<CountState, CountEvent, CountSideEffect>
        get() = buildReducer {
            whenEvent<CountEvent.AttemptMagic> {
                dispatch(CountSideEffect.Magic)
            }

            whenEvent<CountEvent.Decrease> {
                next(copy(count = count - 1))
            }

            whenEvent<CountEvent.Increase> {
                next(copy(count = count + 1))
            }

            whenEvent<CountEvent.Increase1000> {
                next(copy(count = count + 1000))
            }
        }

    override val sideEffectHandler: KindaSideEffectHandler<CountState, CountEvent, CountSideEffect>
        get() = buildSideEffectHandler {
                whenSideEffect<CountSideEffect.Magic> {
                    delay(1000)
                    CountEvent.Increase1000
                }
            }
}