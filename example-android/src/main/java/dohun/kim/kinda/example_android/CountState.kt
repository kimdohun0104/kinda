package dohun.kim.kinda.example_android

import dohun.kim.kinda.kinda_core.KindaEvent
import dohun.kim.kinda.kinda_core.KindaSideEffect
import dohun.kim.kinda.kinda_core.KindaState

data class CountState(
    val count: Int = 0
) : KindaState

sealed class CountEvent : KindaEvent {
    object AttemptMagic : CountEvent()
    object Increase : CountEvent()
    object Decrease : CountEvent()
    object Increase1000 : CountEvent()
}

sealed class CountSideEffect : KindaSideEffect {
    object Magic : CountSideEffect()
}