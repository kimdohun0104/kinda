package dohun.kim.kinda.example_android.login

import dohun.kim.kinda.kinda_core.Event
import dohun.kim.kinda.kinda_core.KindaEvent
import dohun.kim.kinda.kinda_core.KindaSideEffect
import dohun.kim.kinda.kinda_core.KindaState

data class LoginState(
    val isLoginBtnEnable: Boolean = false,
    val isLoading: Boolean = false,

    val email: String = "",
    val password: String = "",

    val toastEvent: Event<String> = Event(),
    val navigateEvent: Event<Unit> = Event()
) : KindaState

sealed class LoginEvent : KindaEvent {
    data class InputEmail(val email: String) : LoginEvent()
    data class InputPassword(val password: String) : LoginEvent()

    object AttemptLogin : LoginEvent()

    object LoginSuccess : LoginEvent()
    object LoginFailure : LoginEvent()
}

sealed class LoginSideEffect : KindaSideEffect {
    object Login : LoginSideEffect()
}