package com.dohun.kindamvi.login

import android.util.Log
import com.dohun.kinda.android.KindaViewModel
import com.dohun.kinda.core.KindaStateMachine
import com.dohun.kinda.core.buildStateMachine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val loginNavigator: LoginNavigator
) : KindaViewModel<LoginState, LoginEvent, LoginSideEffect>() {
    override val stateMachine: KindaStateMachine<LoginState, LoginEvent, LoginSideEffect>
        get() = buildStateMachine(LoginState()) {
            whenEvent<LoginEvent.AttemptLogin> {
                next(copy(isLoggingIn = true), LoginSideEffect.Login)
            }

            whenEvent<LoginEvent.EmailInputChanged> {
                next(
                    copy(
                        email = it.email,
                        isLoginEnable = checkInputValidation(it.email, password)
                    )
                )
            }

            whenEvent<LoginEvent.PasswordInputChanged> {
                next(
                    copy(
                        password = it.password,
                        isLoginEnable = checkInputValidation(email, password)
                    )
                )
            }

            whenEvent<LoginEvent.NavigateToMain> {
                dispatch(LoginSideEffect.NavigateToMain)
            }

            whenIoTask<LoginSideEffect.Login> {
                login()
            }

            whenIoTask<LoginSideEffect.NavigateToMain> {
                navigateToMain()
            }
        }

    private fun checkInputValidation(email: String, password: String) =
        email.length > 4 && password.length > 4

    private suspend fun login() = withContext(Dispatchers.IO) {
        delay(2000)
        LoginEvent.NavigateToMain
    }

    private suspend fun navigateToMain() = withContext(Dispatchers.Main) {
        loginNavigator.navigateToMain()
    }
}