package com.dohun.kinda.android

import com.dohun.kinda.core.concept.KindaEvent
import com.dohun.kinda.core.concept.KindaSideEffect
import com.dohun.kinda.core.concept.KindaState

object KindaLogger {

    private var isLogEnable = true

    fun setIsLogEnable(enable: Boolean) {
        isLogEnable = enable
    }

    fun log(event: KindaEvent) {
        if (!isLogEnable) return
        println("Kinda: Event [${event}]")
    }

    fun log(from: KindaState, next: KindaState) {
        if (!isLogEnable) return
        println("Kinda:     From  [${from}]")
        println("Kinda:     Next  [${next}]")
    }

    fun log(sideEffect: KindaSideEffect) {
        if (!isLogEnable) return
        println("Kinda: Side Effect [${sideEffect}]")
    }
}
