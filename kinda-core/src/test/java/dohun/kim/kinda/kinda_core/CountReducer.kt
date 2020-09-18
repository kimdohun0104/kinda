package dohun.kim.kinda.kinda_core

class CountReducer : KindaReducer<CountState, CountEvent, CountSideEffect> {
    override fun reduce(
        state: CountState,
        event: CountEvent
    ): Next<CountState, CountSideEffect> {
        return when (event) {
            is CountEvent.Increase -> Next(state.copy(count = state.count + 1))
            is CountEvent.Decrease -> Next(state.copy(count = state.count - 1))
            is CountEvent.Increase1000 -> Next(state.copy(count = state.count + 1000))
            is CountEvent.Magic -> Next(null, CountSideEffect.Magic)
        }
    }

}