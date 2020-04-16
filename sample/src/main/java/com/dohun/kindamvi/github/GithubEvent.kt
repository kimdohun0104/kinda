package com.dohun.kindamvi.github

import com.dohun.kinda.core.KindaEvent

sealed class GithubEvent : KindaEvent {
    data class UserLoaded(val users: List<UserModel>) : GithubEvent()

    data class UserLoadFailed(val errorMessage: String) : GithubEvent()

    object AttemptUserLoad : GithubEvent()

    object TestEvent : GithubEvent()
}