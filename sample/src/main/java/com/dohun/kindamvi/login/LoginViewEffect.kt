package com.dohun.kindamvi.login

sealed class LoginViewEffect {
    object NavigateToMain : LoginViewEffect()
}