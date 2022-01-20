package com.guichristovao.appstartup.feature_one

class GitHubRepository(
    private val gitHubService: GitHubService
) {

    suspend fun getUser(
        user: String?
    ) = gitHubService.getUser(user)
}
