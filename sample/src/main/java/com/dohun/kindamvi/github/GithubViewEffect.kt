package com.dohun.kindamvi.github

sealed class GithubViewEffect {
    data class ShowSnackbar(val message: String): GithubViewEffect()
}