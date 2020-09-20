package dohun.kim.kinda.example_android.count

import dohun.kim.kinda.kinda_core.Event
import dohun.kim.kinda.kinda_core.KindaReducer
import dohun.kim.kinda.kinda_core.Next

class CountReducer : KindaReducer<CountState, CountEvent, CountSideEffect> {
    override fun reduce(state: CountState, event: CountEvent): Next<CountState, CountSideEffect> {
        return when (event) {
            CountEvent.AttemptMagic -> Next(null, CountSideEffect.Magic)
            CountEvent.Increase -> Next(state.copy(count = state.count + 1))
            CountEvent.Decrease -> Next(state.copy(count = state.count - 1))
            CountEvent.Increase1000 -> Next(
                state.copy(
                    count = state.count + 1000,
                    toastEvent = Event("Increase 1000, It's magic!")
                )
            )
        }
    }
}