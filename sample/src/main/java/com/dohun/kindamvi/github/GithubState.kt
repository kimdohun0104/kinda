package com.dohun.kindamvi.github

import com.dohun.kinda.core.concept.KindaEvent
import com.dohun.kinda.core.concept.KindaSideEffect
import com.dohun.kinda.core.concept.KindaState
import com.dohun.kinda.core.concept.KindaViewEffect
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
