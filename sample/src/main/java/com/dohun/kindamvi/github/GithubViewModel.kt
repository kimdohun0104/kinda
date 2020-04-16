package com.dohun.kindamvi.github

import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import com.dohun.kinda.android.KindaViewModel
import com.dohun.kinda.core.KindaEvent
import com.dohun.kinda.core.KindaStateMachine
import com.dohun.kinda.core.buildStateMachine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GithubViewModel(private val githubApi: GithubApi) :
    KindaViewModel<GithubState, GithubEvent, GithubSideEffect, GithubViewEffect>() {

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
                next(copy(isLoading = true), GithubSideEffect.AttemptGetUsers)
            }

            whenEvent<GithubEvent.TestEvent> {
                next(copy())
            }

            whenSideEffect<GithubSideEffect.AttemptGetUsers> {
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