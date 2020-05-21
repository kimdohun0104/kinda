package com.dohun.kindamvi.main

import com.dohun.kinda.core.concept.KindaEvent
import com.dohun.kinda.core.concept.KindaSideEffect
import com.dohun.kinda.core.concept.KindaState
import com.dohun.kinda.core.concept.KindaViewEffect

data class MainState(
    val count: Int = 0
) : KindaState

sealed class MainEvent : KindaEvent {
    object Increase : MainEvent()
    object Decrease : MainEvent()
    object Increase1000 : MainEvent()
    object OnClickIncrease1000 : MainEvent()
}

sealed class MainSideEffect : KindaSideEffect {
    object DelayForIncrease : MainSideEffect()
}

sealed class MainViewEffect : KindaViewEffect {

}