package com.dohun.kindamvi.login

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

            whenEvent<LoginEvent.EmailInputChanged> { event ->
                next(copy(email = event.email, isLoginEnable = checkInputValidation(event.email, password)))
            }

            whenEvent<LoginEvent.PasswordInputChanged> { event ->
                next(copy(password = event.password, isLoginEnable = checkInputValidation(email, event.password)))
            }

            whenEvent<LoginEvent.NavigateToMain> {
                dispatch(LoginSideEffect.NavigateToMain)
            }

            whenSideEffect<LoginSideEffect.Login> {
                login()
            }

            whenSideEffect<LoginSideEffect.NavigateToMain> {
                withContext(Dispatchers.Main) {
                    loginNavigator.navigateToMain()
                }
            }
        }

    private fun checkInputValidation(email: String, password: String) =
        email.length > 4 && password.length > 4

    private suspend fun login() = withContext(Dispatchers.IO) {
        delay(2000)
        LoginEvent.NavigateToMain
    }
}