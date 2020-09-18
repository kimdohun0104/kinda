package dohun.kim.kinda.kinda_core

data class CountState(
    val count: Int = 0
): KindaState

sealed class CountEvent: KindaEvent {
    object Increase: CountEvent()
    object Decrease: CountEvent()
    object Increase1000: CountEvent()
    object Magic: CountEvent()
}

sealed class CountSideEffect: KindaSideEffect {
    object Magic: CountSideEffect()
}