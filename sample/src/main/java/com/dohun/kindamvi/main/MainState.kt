package com.dohun.kindamvi.main

import com.dohun.kinda.core.KindaEvent
import com.dohun.kinda.core.KindaSideEffect
import com.dohun.kinda.core.KindaState
import com.dohun.kinda.core.KindaViewEffect

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