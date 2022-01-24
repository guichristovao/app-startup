package com.guichristovao.appstartup.github.data

import com.guichristovao.appstartup.github.data.source.remote.GitHubService
import javax.inject.Inject

class GitHubRepository @Inject constructor(
    private val gitHubService: GitHubService
) {

    suspend fun getUser(
        user: String?
    ) = gitHubService.getUser(user)
}
