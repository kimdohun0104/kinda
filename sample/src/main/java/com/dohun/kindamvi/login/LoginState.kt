package com.dohun.kindamvi.login

import com.dohun.kinda.core.KindaState

data class LoginState(

    val isLoggingIn: Boolean = false,

    val isLoginEnable: Boolean = false,

    val email: String = "",

    val password: String = ""
) : KindaState