package dohun.kim.kinda.example_android.login

import dohun.kim.kinda.kinda_core.Event
import dohun.kim.kinda.kinda_core.KindaReducer
import dohun.kim.kinda.kinda_core.Next

class LoginReducer : KindaReducer<LoginState, LoginEvent, LoginSideEffect> {
    override fun reduce(state: LoginState, event: LoginEvent): Next<LoginState, LoginSideEffect> {
        return when (event) {
            is LoginEvent.InputPassword ->
                Next(state.copy(password = event.password, isLoginBtnEnable = isLoginEnable(state.email, event.password)))
            is LoginEvent.InputEmail ->
                Next(state.copy(email = event.email, isLoginBtnEnable = isLoginEnable(event.email, state.password)))

            is LoginEvent.AttemptLogin -> Next(state.copy(isLoading = true), LoginSideEffect.Login)

            is LoginEvent.LoginSuccess -> Next(state.copy(navigateEvent = Event(Unit), isLoading = false))
            is LoginEvent.LoginFailure -> Next(state.copy(toastEvent = Event("로그인에 실패했습니다"), isLoading = false))
        }
    }

    private fun isLoginEnable(email: String, password: String): Boolean {
        return email.isNotBlank() && password.isNotBlank()
    }
}