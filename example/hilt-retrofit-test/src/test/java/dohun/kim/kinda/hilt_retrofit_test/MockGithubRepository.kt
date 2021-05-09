package dohun.kim.kinda.hilt_retrofit_test

import androidx.annotation.VisibleForTesting
import dohun.kim.kinda.hilt_retrofit_test.data.GithubRepository
import dohun.kim.kinda.hilt_retrofit_test.data.exception.ForbiddenException
import dohun.kim.kinda.hilt_retrofit_test.model.UserModel
import java.lang.IllegalStateException

class MockGithubRepository : GithubRepository {

    private var users: List<UserModel>? = null
    private var exceptionShouldThrown: Exception? = null

    override suspend fun getUsers(): List<UserModel> {
        exceptionShouldThrown?.let {
            throw it
        }
        users?.let {
            return it
        }
        throw IllegalStateException()
    }

    fun setExceptionShouldThrown(exception: Exception) {
        this.exceptionShouldThrown = exception
    }

    fun setUsers(users: List<UserModel>) {
        this.users = users
    }
}