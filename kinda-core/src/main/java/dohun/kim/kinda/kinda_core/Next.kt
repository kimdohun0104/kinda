package dohun.kim.kinda.kinda_core

data class Next<S : KindaState, SE : KindaSideEffect>(
    val state: S?,
    val sideEffect: SE? = null
)
