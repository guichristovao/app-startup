package com.guichristovao.appstartup.profile.data.source

import com.guichristovao.appstartup.profile.data.model.GitHubUser

class FakeProfileRepository : ProfileRepository {

    override suspend fun getUser(user: String?): GitHubUser {
        return user?.run {
            GitHubUser(user, "fakeAvatarUrl", "fakeName")
        } ?: throw RuntimeException("fakeMessage")
    }
}
