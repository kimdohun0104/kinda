package com.dohun.kindamvi

import com.dohun.kinda.KindaEvent

sealed class MainEvent : KindaEvent {
    object Increase : MainEvent()
    object Decrease : MainEvent()
    object Increase1000 :MainEvent()
}