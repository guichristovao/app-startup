package com.guichristovao.appstartup.profile.data.source

import com.google.common.truth.Truth.assertThat
import com.guichristovao.appstartup.profile.data.model.GitHubUser
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DefaultProfileRepositoryTest {

    private val remoteGitHubUser = GitHubUser("remoteLogin", "remoteAvatarUrl", "remoteName")
    private lateinit var remoteDataSource: ProfileDataSource
    private lateinit var repository: ProfileRepository

    @Before
    fun setup() {
        remoteDataSource = FakeDataSource(remoteGitHubUser)
        repository = DefaultProfileRepository(remoteDataSource)
    }

    @Test
    fun getUser_returnsGitHubUser() = runTest {
        val actualGitHubUser = repository.getUser("fakeUser")

        assertThat(actualGitHubUser).isEqualTo(remoteGitHubUser)
    }
}
