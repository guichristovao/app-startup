package com.guichristovao.appstartup.profile.data.source

import com.guichristovao.appstartup.profile.data.model.GitHubUser

interface ProfileRepository {

    suspend fun getUser(user: String?): GitHubUser

}
