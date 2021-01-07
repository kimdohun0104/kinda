package dohun.kim.kinda.example_android.count

import dohun.kim.kinda.kinda_core.KindaSideEffectHandler
import kotlinx.coroutines.delay

class CountSideEffectHandler : KindaSideEffectHandler<CountState, CountEvent, CountSideEffect> {
    override suspend fun handle(sideEffect: CountSideEffect): CountEvent {
        return when (sideEffect) {
            CountSideEffect.Magic -> {
                delay(1000)
                CountEvent.Increase1000
            }
        }
    }
}