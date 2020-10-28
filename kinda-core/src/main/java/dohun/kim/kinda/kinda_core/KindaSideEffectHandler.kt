package dohun.kim.kinda.kinda_core

interface KindaSideEffectHandler<S : KindaState, E : KindaEvent, SE : KindaSideEffect> {
    suspend fun handle(sideEffect: SE): E
}