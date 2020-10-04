package dohun.kim.kinda.hilt_retrofit_test.data

import dohun.kim.kinda.hilt_retrofit_test.data.exception.getCustomHttpException
import dohun.kim.kinda.hilt_retrofit_test.model.UserModel
import javax.inject.Inject

interface GithubRepository {

    suspend fun getUsers(): List<UserModel>
}

class DefaultGithubRepository @Inject constructor(
    private val githubApi: GithubApi
) : GithubRepository {

    override suspend fun getUsers(): List<UserModel> =
        try {
            githubApi.getUsers()
        } catch (e: Exception) {
            throw e.getCustomHttpException()
        }
}