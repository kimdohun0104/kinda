package dohun.kim.kinda.kinda_dsl

import dohun.kim.kinda.kinda_core.KindaEvent
import dohun.kim.kinda.kinda_core.KindaSideEffect
import dohun.kim.kinda.kinda_core.KindaSideEffectHandler
import dohun.kim.kinda.kinda_core.KindaState

class SideEffectHandlerDslBuilder<S : KindaState, E : KindaEvent, SE : KindaSideEffect> {

    val sideEffectHandleMap = HashMap<SE, suspend S.() -> E>()

    fun whenSideEffect(
        sideEffect: SE,
        next: suspend S.() -> E
    ) {
        sideEffectHandleMap[sideEffect] = next
    }

    fun build() = object : KindaSideEffectHandler<S, E, SE> {
        override suspend fun handle(state: S, sideEffect: SE): E {
            sideEffectHandleMap.keys.forEach { key ->
                if (key::class.isInstance(sideEffect)) {
                    return sideEffectHandleMap[key]!!.invoke(state)
                }
            }
            throw IllegalStateException("SideEffect not handled ${sideEffect::class}")
        }
    }
}

fun <S : KindaState, E : KindaEvent, SE : KindaSideEffect> buildSideEffectHandler(
    init: SideEffectHandlerDslBuilder<S, E, SE>.() -> Unit
) = SideEffectHandlerDslBuilder<S, E, SE>().apply(init).build()