package dohun.kim.kinda.count

import dohun.kim.kinda.kinda_core.KindaEvent
import dohun.kim.kinda.kinda_core.KindaSideEffect
import dohun.kim.kinda.kinda_core.KindaState

data class CountState(
    val count: Int = 0
) : KindaState

sealed class CountEvent : KindaEvent {
    object AttemptGetCount : CountEvent()

    data class SetCount(val count: Int) : CountEvent()

    object Increase : CountEvent()
}

sealed class CountSideEffect : KindaSideEffect {
    object GetCount : CountSideEffect()
}