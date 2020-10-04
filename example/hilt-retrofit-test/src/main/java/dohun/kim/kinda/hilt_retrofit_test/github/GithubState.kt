package dohun.kim.kinda.hilt_retrofit_test.github

import dohun.kim.kinda.hilt_retrofit_test.model.UserModel
import dohun.kim.kinda.kinda_core.Event
import dohun.kim.kinda.kinda_core.KindaEvent
import dohun.kim.kinda.kinda_core.KindaSideEffect
import dohun.kim.kinda.kinda_core.KindaState

data class GithubState(
    val isLoading: Boolean = false,
    val users: List<UserModel> = emptyList(),

    val toastEvent: Event<String> = Event()
) : KindaState

sealed class GithubEvent : KindaEvent {
    object AttemptGetUsers : GithubEvent()
    data class GetUsersSucceed(val users: List<UserModel>) : GithubEvent()
    data class ToastEvent(val message: String) : GithubEvent()
}

sealed class GithubSideEffect : KindaSideEffect {
    object GetUsers : GithubSideEffect()
}