package com.dohun.kindamvi.github

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GithubApi {

    @GET("users")
    suspend fun getUsers(): List<UserModel>

    companion object {
        fun getGithubApi(): GithubApi =
            Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubApi::class.java)
    }
}