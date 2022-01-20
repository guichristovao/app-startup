package com.guichristovao.appstartup.feature_one

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{user}")
    suspend fun getUser(
        @Path("user") user: String?
    ): Response<GitHubUser>
}
