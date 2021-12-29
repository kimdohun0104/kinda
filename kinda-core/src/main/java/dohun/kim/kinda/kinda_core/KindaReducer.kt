package dohun.kim.kinda.kinda_core

interface KindaReducer<S : KindaState, E: KindaEvent, SE: KindaSideEffect> {
    fun reduce(state: S, event: E): Next<S, SE>
}