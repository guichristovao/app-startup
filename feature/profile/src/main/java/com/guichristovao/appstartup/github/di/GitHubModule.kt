package com.guichristovao.appstartup.github.di

import com.guichristovao.appstartup.github.GitHub
import com.guichristovao.appstartup.github.data.GitHubRepository
import com.guichristovao.appstartup.github.data.source.remote.GitHubService
import com.guichristovao.appstartup.network_support.ExceptionHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object GitHubModule {

    @Provides
    fun provideGitHubService(): GitHubService {
        return GitHub.serviceCreator.create(GitHubService::class.java) as GitHubService
    }

    @Provides
    fun provideGitHubRepository(service: GitHubService): GitHubRepository {
        return GitHubRepository(service)
    }

    @Provides
    fun provideExceptionHandler(): ExceptionHandler {
        return GitHub.exceptionHandler
    }
}
