package dohun.kim.kinda.kinda_core.logging

import dohun.kim.kinda.kinda_core.KindaEvent
import dohun.kim.kinda.kinda_core.KindaSideEffect
import dohun.kim.kinda.kinda_core.KindaState

internal var kindaLogger: KindaLogger? = null

object KindaLoggerSetting {

    fun enableLogging(logPrefix: String = "Kinda log: ") {
        kindaLogger = KindaLogger(logPrefix)
    }

    fun disableLogging() {
        kindaLogger = null
    }
}

internal class KindaLogger(
    private val logPrefix: String
) {

    fun beforeReduce(before: KindaState, event: KindaEvent) {
        println("${logPrefix}Event [${event::class.java.simpleName}]")
        println("${logPrefix}\tFrom [${before}]")
    }

    fun afterReduce(next: KindaState?) {
        println("${logPrefix}\tNext [${next ?: "No changes(null)"}]")
    }

    fun beforeHandleSideEffect(sideEffect: KindaSideEffect) {
        println("${logPrefix}SideEffect start [${sideEffect::class.java.simpleName}]")
    }

    fun afterHandleSideEffect(sideEffectResult: KindaEvent, sideEffect: KindaSideEffect) {
        println("${logPrefix}SideEffect end [${sideEffect::class.java.simpleName}]")
        println("${logPrefix}\tSideEffectResult [${sideEffectResult::class.java.simpleName}]")
    }
}