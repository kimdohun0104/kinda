package com.dohun.kindamvi.github

import com.dohun.kinda.android.KindaViewModel
import com.dohun.kinda.core.stateMachine.KindaStateMachine
import com.dohun.kinda.core.stateMachine.buildStateMachine
import com.dohun.kindamvi.data.GithubApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GithubViewModel(private val githubApi: GithubApi) :
    KindaViewModel<GithubState, GithubEvent, GithubSideEffect, GithubViewEffect>() {

    init {
        intent(GithubEvent.AttemptUserLoad)
    }

    override val stateMachine: KindaStateMachine<GithubState, GithubEvent, GithubSideEffect>
        get() = buildStateMachine(GithubState()) {
            whenEvent<GithubEvent.UserLoaded> {
                next(copy(isLoading = false, users = it.users))
            }

            whenEvent<GithubEvent.UserLoadFailed> {
                viewEffect(GithubViewEffect.ShowSnackbar(it.errorMessage))
                noChange()
            }

            whenEvent<GithubEvent.AttemptUserLoad> {
                next(copy(isLoading = true), GithubSideEffect.GetUsers)
            }

            whenSideEffect<GithubSideEffect.GetUsers> {
                withContext(Dispatchers.IO) {
                    try {
                        GithubEvent.UserLoaded(githubApi.getUsers())
                    } catch (e: Exception) {
                        GithubEvent.UserLoadFailed("Failed")
                    }
                }
            }
        }
}