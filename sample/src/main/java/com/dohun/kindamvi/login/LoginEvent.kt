package com.dohun.kindamvi.login

import android.app.Activity
import com.dohun.kinda.core.KindaEvent

sealed class LoginEvent : KindaEvent {

    data class EmailInputChanged(val email: String) : LoginEvent()

    data class PasswordInputChanged(val password: String) : LoginEvent()

    object AttemptLogin : LoginEvent()

    object NavigateToMain : LoginEvent()
}