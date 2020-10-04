package dohun.kim.kinda.hilt_retrofit_test.data

import dohun.kim.kinda.hilt_retrofit_test.model.UserModel
import retrofit2.http.GET

interface GithubApi {

    @GET("users")
    suspend fun getUsers(): List<UserModel>
}