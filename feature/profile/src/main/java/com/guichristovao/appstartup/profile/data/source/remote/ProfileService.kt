package com.guichristovao.appstartup.profile.data.source.remote

import com.guichristovao.appstartup.profile.data.model.GitHubUser
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileService {

    @GET("users/{user}")
    suspend fun getUser(
        @Path("user") user: String?
    ): GitHubUser
}
