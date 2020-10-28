package dohun.kim.kinda.kinda_core.interceptor

import dohun.kim.kinda.kinda_core.KindaEvent
import dohun.kim.kinda.kinda_core.KindaSideEffect
import dohun.kim.kinda.kinda_core.KindaState

@Deprecated("Interceptor deprecated since 1.3.0")
open class Interceptor<S : KindaState, E : KindaEvent, SE : KindaSideEffect> {

    open fun beforeReduce(before: S, event: E) {
    }

    open fun afterReduce(next: S?, event: E) {

    }

    open fun beforeHandleSideEffect(state: S, sideEffect: SE) {

    }

    open fun afterHandleSideEffect(state: S, sideEffectResult: E, sideEffect: SE) {

    }
}

fun <S : KindaState, E : KindaEvent, SE : KindaSideEffect>
        Set<Interceptor<S, E, SE>>.invokeBeforeReduce(state: S, event: E) =
    this.forEach { it.beforeReduce(state, event) }

fun <S : KindaState, E : KindaEvent, SE : KindaSideEffect>
        Set<Interceptor<S, E, SE>>.invokeAfterReduce(state: S?, event: E) =
    this.forEach { it.afterReduce(state, event) }

fun <S : KindaState, E : KindaEvent, SE : KindaSideEffect>
        Set<Interceptor<S, E, SE>>.invokeBeforeHandleSideEffect(state: S, sideEffect: SE) =
    this.forEach { it.beforeHandleSideEffect(state, sideEffect) }

fun <S : KindaState, E : KindaEvent, SE : KindaSideEffect>
        Set<Interceptor<S, E, SE>>.invokeAfterHandleSideEffect(state: S, event: E, sideEffect: SE) =
    this.forEach { it.afterHandleSideEffect(state, event, sideEffect) }
