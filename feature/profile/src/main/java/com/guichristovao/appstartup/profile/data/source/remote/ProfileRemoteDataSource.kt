package com.guichristovao.appstartup.profile.data.source.remote

import com.guichristovao.appstartup.profile.data.source.ProfileDataSource
import com.guichristovao.appstartup.profile.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRemoteDataSource @Inject constructor(
    private val profileService: ProfileService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ProfileDataSource {

    override suspend fun getUser(
        user: String?
    ) = withContext(ioDispatcher) {
        profileService.getUser(user)
    }
}
