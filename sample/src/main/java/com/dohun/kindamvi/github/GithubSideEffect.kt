package com.dohun.kindamvi.github

import com.dohun.kinda.core.KindaSideEffect

sealed class GithubSideEffect : KindaSideEffect {
    object AttemptGetUsers : GithubSideEffect()
}