package com.dohun.kindamvi.login

import com.dohun.kinda.core.concept.KindaEvent
import com.dohun.kinda.core.concept.KindaSideEffect
import com.dohun.kinda.core.concept.KindaState
import com.dohun.kinda.core.concept.KindaViewEffect

data class LoginState(
    val isLoggingIn: Boolean = false,
    val isLoginEnable: Boolean = false,
    val email: String = "",
    val password: String = ""
) : KindaState

sealed class LoginEvent : KindaEvent {
    data class EmailInputChanged(val email: String) : LoginEvent()
    data class PasswordInputChanged(val password: String) : LoginEvent()
    object AttemptLogin : LoginEvent()
    object NavigateToMain : LoginEvent()
}

sealed class LoginSideEffect : KindaSideEffect {
    object Login : LoginSideEffect()
}

sealed class LoginViewEffect : KindaViewEffect {
    object NavigateToMain : LoginViewEffect()
}