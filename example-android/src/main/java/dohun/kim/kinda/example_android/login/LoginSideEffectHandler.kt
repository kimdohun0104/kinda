package dohun.kim.kinda.example_android.login

import dohun.kim.kinda.kinda_core.KindaSideEffectHandler
import kotlinx.coroutines.delay

class LoginSideEffectHandler : KindaSideEffectHandler<LoginState, LoginEvent, LoginSideEffect> {
    override suspend fun handle(state: LoginState, sideEffect: LoginSideEffect): LoginEvent {
        return when (sideEffect) {
            is LoginSideEffect.Login -> {
                someLoginLogic()
                LoginEvent.LoginSuccess
            }
        }
    }

    private suspend fun someLoginLogic() {
        delay(2000)
    }
}