package com.guichristovao.appstartup.profile.data.source

import com.guichristovao.appstartup.profile.di.ProfileDataSourceModule
import javax.inject.Inject

class DefaultProfileRepository @Inject constructor(
    @ProfileDataSourceModule.ProfileRemoteDataSource private val profileRemoteDataSource: ProfileDataSource
) : ProfileRepository {

    override suspend fun getUser(
        user: String?
    ) = profileRemoteDataSource.getUser(user)
}
