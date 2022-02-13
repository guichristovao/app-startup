package com.guichristovao.appstartup.profile.data.source

import com.guichristovao.appstartup.profile.di.ProfileModule.ProfileRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultProfileRepository @Inject constructor(
    @ProfileRemoteDataSource private val profileRemoteDataSource: ProfileDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ProfileRepository {

    override suspend fun getUser(
        user: String?
    ) = withContext(ioDispatcher) {
        profileRemoteDataSource.getUser(user)
    }
}
