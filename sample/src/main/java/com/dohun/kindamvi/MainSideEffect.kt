package com.dohun.kindamvi

import com.dohun.kinda.core.KindaSideEffect

sealed class MainSideEffect : KindaSideEffect {
    object Nothing: MainSideEffect()
}