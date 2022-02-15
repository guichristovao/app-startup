package com.guichristovao.appstartup.profile.data.source

import com.guichristovao.appstartup.profile.data.model.GitHubUser

class FakeDataSource(
    private val gitHubUser: GitHubUser
) : ProfileDataSource {

    override suspend fun getUser(user: String?): GitHubUser {
        return gitHubUser
    }
}
