package com.dohun.kindamvi.main

import com.dohun.kinda.core.KindaSideEffect

sealed class MainSideEffect : KindaSideEffect {
    object Nothing: MainSideEffect()
}