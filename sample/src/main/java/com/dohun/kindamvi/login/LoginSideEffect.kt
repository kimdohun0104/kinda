package com.dohun.kindamvi.login

import com.dohun.kinda.core.KindaSideEffect

sealed class LoginSideEffect : KindaSideEffect {
    object Login : LoginSideEffect()
    object NavigateToMain : LoginSideEffect()
}