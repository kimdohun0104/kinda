package com.dohun.kinda.core

sealed class KindaOutput<out S : KindaState, out E : KindaEvent, out SE : KindaSideEffect> {
    abstract val from: S
    abstract val event: E

    data class Valid<out S : KindaState, out E : KindaEvent, out SE : KindaSideEffect>(
        override val from: S,
        override val event: E,
        val next: S,
        val sideEffect: SE?
    ) : KindaOutput<S, E, SE>()

    data class Void<out S : KindaState, out E : KindaEvent, out SE : KindaSideEffect>(
        override val from: S,
        override val event: E
    ) : KindaOutput<S, E, SE>()
}