package com.guichristovao.appstartup.profile.data

import com.guichristovao.appstartup.profile.data.source.remote.ProfileService
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profileService: ProfileService
) {

    suspend fun getUser(
        user: String?
    ) = profileService.getUser(user)
}
