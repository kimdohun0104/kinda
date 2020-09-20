package dohun.kim.kinda.example_android.login

import dohun.kim.kinda.kinda_android.KindaViewModel

class LoginViewModel : KindaViewModel<LoginState, LoginEvent, LoginSideEffect>(
    initialState = LoginState(),
    reducer = LoginReducer(),
    sideEffectHandler = LoginSideEffectHandler()
) {
}