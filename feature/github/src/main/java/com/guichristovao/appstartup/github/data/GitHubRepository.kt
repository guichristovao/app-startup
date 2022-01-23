package com.guichristovao.appstartup.github.data

import com.guichristovao.appstartup.github.data.source.remote.GitHubService

class GitHubRepository(
    private val gitHubService: GitHubService
) {

    suspend fun getUser(
        user: String?
    ) = gitHubService.getUser(user)
}
