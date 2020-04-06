package com.dohun.kindamvi.main

import com.dohun.kinda.core.KindaEvent

sealed class MainEvent : KindaEvent {
    object Increase : MainEvent()
    object Decrease : MainEvent()
    object Increase1000 : MainEvent()
    object OnClickIncrease1000: MainEvent()
}