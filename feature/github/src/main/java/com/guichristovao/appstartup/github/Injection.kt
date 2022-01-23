package com.guichristovao.appstartup.github

import com.guichristovao.appstartup.github.data.GitHubRepository
import com.guichristovao.appstartup.github.data.source.remote.GitHubService
import com.guichristovao.appstartup.github.ui.state.GitHubViewModel

object Injection {

    fun provideGitHubVMFactory(repository: GitHubRepository): GitHubViewModel.Factory {
        return GitHubViewModel.Factory(repository)
    }

    fun provideGitHubRepository(service: GitHubService): GitHubRepository {
        return GitHubRepository(service)
    }

    fun provideGitHubService(): GitHubService {
        return GitHub.serviceCreator.create(GitHubService::class.java) as GitHubService
    }
}
