package com.dohun.kindamvi.github

import com.dohun.kinda.core.KindaState

data class GithubState(
    val isLoading: Boolean = false,

    val users: List<UserModel> = emptyList()
) : KindaState