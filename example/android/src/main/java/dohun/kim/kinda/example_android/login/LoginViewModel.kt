package dohun.kim.kinda.example_android.login

import dohun.kim.kinda.kinda_android.KindaViewModel
import dohun.kim.kinda.kinda_core.KindaReducer
import dohun.kim.kinda.kinda_core.KindaSideEffectHandler

class LoginViewModel : KindaViewModel<LoginState, LoginEvent, LoginSideEffect>(
    initialState = LoginState()
) {
    override val reducer: KindaReducer<LoginState, LoginEvent, LoginSideEffect>
        get() = LoginReducer()
    override val sideEffectHandler: KindaSideEffectHandler<LoginState, LoginEvent, LoginSideEffect>
        get() = LoginSideEffectHandler()
}