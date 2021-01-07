package dohun.kim.kinda.hilt_retrofit_test.github

import androidx.hilt.lifecycle.ViewModelInject
import dohun.kim.kinda.hilt_retrofit_test.data.GithubRepository
import dohun.kim.kinda.hilt_retrofit_test.data.exception.ForbiddenException
import dohun.kim.kinda.hilt_retrofit_test.data.exception.InternalErrorException
import dohun.kim.kinda.kinda_android.KindaViewModel
import dohun.kim.kinda.kinda_core.Event
import dohun.kim.kinda.kinda_core.KindaReducer
import dohun.kim.kinda.kinda_core.KindaSideEffectHandler
import dohun.kim.kinda.kinda_dsl.buildReducer
import dohun.kim.kinda.kinda_dsl.buildSideEffectHandler

class GithubViewModel @ViewModelInject constructor(
    private val githubRepository: GithubRepository
) : KindaViewModel<GithubState, GithubEvent, GithubSideEffect>(
    initialState = GithubState(),
) {
    override val reducer: KindaReducer<GithubState, GithubEvent, GithubSideEffect>
        get() = buildReducer {
            whenEvent<GithubEvent.AttemptGetUsers> {
                next(copy(isLoading = true), GithubSideEffect.GetUsers)
            }

            whenEvent<GithubEvent.GetUsersSucceed> {
                next(copy(users = it.users, isLoading = false))
            }

            whenEvent<GithubEvent.ToastEvent> {
                next(copy(toastEvent = Event(it.message), isLoading = false))
            }
        }

    override val sideEffectHandler: KindaSideEffectHandler<GithubState, GithubEvent, GithubSideEffect>
        get() = buildSideEffectHandler {
            whenSideEffect<GithubSideEffect> {
                try {
                    val users = githubRepository.getUsers()
                    GithubEvent.GetUsersSucceed(users)
                } catch (e: InternalErrorException) {
                    GithubEvent.ToastEvent("서버 문제가 발생했습니다.")
                } catch (e: ForbiddenException) {
                    GithubEvent.ToastEvent("잠시 후 다시 시도해주세요.")
                }
            }
        }
}