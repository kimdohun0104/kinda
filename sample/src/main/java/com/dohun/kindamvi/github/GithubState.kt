package com.dohun.kindamvi.github

import com.dohun.kinda.core.KindaEvent
import com.dohun.kinda.core.KindaSideEffect
import com.dohun.kinda.core.KindaState
import com.dohun.kinda.core.KindaViewEffect
import com.dohun.kindamvi.data.model.UserModel

data class GithubState(
    val isLoading: Boolean = false,
    val users: List<UserModel> = emptyList()
) : KindaState

sealed class GithubEvent : KindaEvent {
    data class UserLoaded(val users: List<UserModel>) : GithubEvent()
    data class UserLoadFailed(val errorMessage: String) : GithubEvent()
    object AttemptUserLoad : GithubEvent()
}

sealed class GithubSideEffect : KindaSideEffect {
    object GetUsers : GithubSideEffect()
}

sealed class GithubViewEffect : KindaViewEffect {
    data class ShowSnackbar(val message: String) : GithubViewEffect()
}
