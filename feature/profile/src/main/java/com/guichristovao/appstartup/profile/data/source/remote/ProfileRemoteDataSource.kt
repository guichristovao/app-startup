package com.guichristovao.appstartup.profile.data.source.remote

import com.guichristovao.appstartup.profile.data.source.ProfileDataSource
import com.guichristovao.appstartup.profile.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRemoteDataSource @Inject constructor(
    private val profileService: ProfileService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ProfileDataSource {

    override suspend fun getUser(
        user: String?
    ) = withContext(ioDispatcher) {
        // Intentionally delays the request, so we can take a better glimpse of the view's
        // loading skeleton
        delay(3000)
        profileService.getUser(user)
    }
}
