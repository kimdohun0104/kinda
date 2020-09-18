package dohun.kim.kinda.kinda_core

import kotlinx.coroutines.delay

class CountSideEffectHandler : KindaSideEffectHandler<CountState, CountEvent, CountSideEffect> {

    override suspend fun handle(
        state: CountState,
        sideEffect: CountSideEffect
    ): CountEvent {
        return when (sideEffect) {
            is CountSideEffect.Magic -> {
                someIoWork()
                CountEvent.Increase1000
            }
        }
    }

    private suspend fun someIoWork() {
        delay(1000)
    }
}

