package dohun.kim.kinda.count

import dohun.kim.kinda.kinda_android.KindaViewModel
import dohun.kim.kinda.kinda_dsl.buildReducer
import dohun.kim.kinda.kinda_dsl.buildSideEffectHandler

class CountViewModel(
    private val countRepository: CountRepository
) : KindaViewModel<CountState, CountEvent, CountSideEffect>(
    initialState = CountState(),
    reducer = buildReducer {
        whenEvent<CountEvent.Increase> {
            next(copy(count = count + 1))
        }

        whenEvent<CountEvent.AttemptGetCount> {
            dispatch(CountSideEffect.GetCount)
        }

        whenEvent<CountEvent.SetCount> {
            next(copy(count = it.count))
        }
    },
    sideEffectHandler = buildSideEffectHandler {
        whenSideEffect<CountSideEffect.GetCount> {
            CountEvent.SetCount(countRepository.getCount())
        }
    }
) {
    init {
        intent(CountEvent.AttemptGetCount)
    }
}