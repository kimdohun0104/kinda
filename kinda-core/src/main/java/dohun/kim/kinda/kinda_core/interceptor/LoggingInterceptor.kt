package dohun.kim.kinda.kinda_core.interceptor

import dohun.kim.kinda.kinda_core.KindaEvent
import dohun.kim.kinda.kinda_core.KindaSideEffect
import dohun.kim.kinda.kinda_core.KindaState

class LoggingInterceptor<S : KindaState, E : KindaEvent, SE : KindaSideEffect> : Interceptor<S, E, SE>() {
    companion object {
        private const val LOG_PREFIX = "Kinda log: "
    }

    override fun beforeReduce(before: S, event: E) {
        println("${LOG_PREFIX}Event [${event::class.java.simpleName}]")
        println("$LOG_PREFIX\tFrom [${before}]")
    }

    override fun afterReduce(next: S?, event: E) {
        println("$LOG_PREFIX\tNext [${next ?: "No changes(null)"}]")
    }

    override fun beforeHandleSideEffect(state: S, sideEffect: SE) {
        println("${LOG_PREFIX}SideEffect start [${sideEffect::class.java.simpleName}]")
    }

    override fun afterHandleSideEffect(state: S, sideEffectResult: E, sideEffect: SE) {
        println("${LOG_PREFIX}SideEffect end [${sideEffect::class.java.simpleName}]")
        println("${LOG_PREFIX}\tSideEffectResult [${sideEffectResult::class.java.simpleName}]")
    }
}