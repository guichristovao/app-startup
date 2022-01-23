package com.guichristovao.appstartup.github.data.source.remote

import com.guichristovao.appstartup.github.data.model.GitHubUser
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{user}")
    suspend fun getUser(
        @Path("user") user: String?
    ): GitHubUser
}
