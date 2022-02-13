package com.guichristovao.appstartup.profile.data.source

import com.guichristovao.appstartup.profile.di.IoDispatcher
import com.guichristovao.appstartup.profile.di.ProfileDataSourceModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultProfileRepository @Inject constructor(
    @ProfileDataSourceModule.ProfileRemoteDataSource private val profileRemoteDataSource: ProfileDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ProfileRepository {

    override suspend fun getUser(
        user: String?
    ) = withContext(ioDispatcher) {
        profileRemoteDataSource.getUser(user)
    }
}
