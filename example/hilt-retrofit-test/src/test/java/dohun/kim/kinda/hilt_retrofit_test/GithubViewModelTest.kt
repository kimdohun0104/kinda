package dohun.kim.kinda.hilt_retrofit_test

import dohun.kim.kinda.hilt_retrofit_test.data.exception.ForbiddenException
import dohun.kim.kinda.hilt_retrofit_test.data.exception.InternalErrorException
import dohun.kim.kinda.hilt_retrofit_test.github.GithubEvent
import dohun.kim.kinda.hilt_retrofit_test.github.GithubSideEffect
import dohun.kim.kinda.hilt_retrofit_test.github.GithubState
import dohun.kim.kinda.hilt_retrofit_test.github.GithubViewModel
import dohun.kim.kinda.hilt_retrofit_test.model.UserModel
import dohun.kim.kinda.kinda_android.KindaViewModel
import dohun.kim.kinda.kinda_android_test.KindaViewModelTest
import dohun.kim.kinda.kinda_core.logging.KindaLoggerSetting
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GithubViewModelTest : KindaViewModelTest<GithubState, GithubEvent, GithubSideEffect>() {

    private lateinit var githubRepository: MockGithubRepository

    override fun buildViewModel(): KindaViewModel<GithubState, GithubEvent, GithubSideEffect> {
        githubRepository = MockGithubRepository()
        return GithubViewModel(githubRepository)
    }

    @Test
    fun `AttemptGetUsers GettingUsersSucceed SetUsersState`() = runBlocking {
        githubRepository.setUsers(users)

        GithubEvent.AttemptGetUsers expectState {
            assertEquals(users, it.users)
            assertEquals(users[0].id, it.users[0].id)
            assertEquals(users.size, it.users.size)
        }
    }

    @Test
    fun `AttemptGetUsers ThrowInternalErrorException ShowErrorMessage`() = runBlocking {
        githubRepository.setExceptionShouldThrown(InternalErrorException())

        GithubEvent.AttemptGetUsers expectState {
            assertEquals("서버 문제가 발생했습니다.", it.toastEvent.peekData())
        }
    }

    @Test
    fun `AttemptGetUsers ThrowForbiddenException ShowErrorMessage`() = runBlocking {
        githubRepository.setExceptionShouldThrown(ForbiddenException())

        GithubEvent.AttemptGetUsers expectState {
            assertEquals("잠시 후 다시 시도해주세요.", it.toastEvent.peekData())
        }
    }

    companion object TestData {
        private val users = listOf(
            UserModel("kimdohun0104", 0, "https://kimdohunimage.jpg"),
            UserModel("hello", 1, "https://helloworld.jpg")
        )
    }
}