package com.guichristovao.appstartup.profile.di

import com.guichristovao.appstartup.profile.ProfileFeature
import com.guichristovao.appstartup.profile.data.ProfileRepository
import com.guichristovao.appstartup.profile.data.source.remote.ProfileService
import com.guichristovao.appstartup.network_support.ExceptionHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object ProfileModule {

    @Provides
    fun provideProfileService(): ProfileService {
        return ProfileFeature.serviceCreator.create(ProfileService::class.java)
    }

    @Provides
    fun provideProfileRepository(service: ProfileService): ProfileRepository {
        return ProfileRepository(service)
    }

    @Provides
    fun provideExceptionHandler(): ExceptionHandler {
        return ProfileFeature.exceptionHandler
    }
}
