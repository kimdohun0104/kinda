package dohun.kim.kinda.example_android.count

import dohun.kim.kinda.kinda_core.Event
import dohun.kim.kinda.kinda_core.KindaEvent
import dohun.kim.kinda.kinda_core.KindaSideEffect
import dohun.kim.kinda.kinda_core.KindaState

data class CountState(
    val count: Int = 0,
    val toastEvent: Event<String> = Event()
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