package com.guichristovao.appstartup.profile.data.source.remote

import com.guichristovao.appstartup.profile.data.source.ProfileDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRemoteDataSource @Inject constructor(
    private val profileService: ProfileService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ProfileDataSource {

    override suspend fun getUser(
        user: String?
    ) = withContext(ioDispatcher) {
        profileService.getUser(user)
    }
}
