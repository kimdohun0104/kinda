package com.dohun.kinda.core

sealed class KindaOutput<out S : com.dohun.kinda.core.concept.KindaState, out E : com.dohun.kinda.core.concept.KindaEvent, out SE : com.dohun.kinda.core.concept.KindaSideEffect> {
    abstract val from: S
    abstract val event: E

    data class Valid<out S : com.dohun.kinda.core.concept.KindaState, out E : com.dohun.kinda.core.concept.KindaEvent, out SE : com.dohun.kinda.core.concept.KindaSideEffect>(
        override val from: S,
        override val event: E,
        val next: S,
        val sideEffect: SE?
    ) : KindaOutput<S, E, SE>()

    data class Void<out S : com.dohun.kinda.core.concept.KindaState, out E : com.dohun.kinda.core.concept.KindaEvent, out SE : com.dohun.kinda.core.concept.KindaSideEffect>(
        override val from: S,
        override val event: E
    ) : KindaOutput<S, E, SE>()
}