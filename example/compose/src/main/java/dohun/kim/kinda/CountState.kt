package dohun.kim.kinda

import dohun.kim.kinda.kinda_core.KindaEvent
import dohun.kim.kinda.kinda_core.KindaSideEffect
import dohun.kim.kinda.kinda_core.KindaState

data class CountState(
    val count: Int = 0
) : KindaState

sealed class CountEvent : KindaEvent {
    object Increase : CountEvent()
}

sealed class CountSideEffect : KindaSideEffect {
}