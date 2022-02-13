package com.guichristovao.appstartup.profile.di

import com.guichristovao.appstartup.network_support.ExceptionHandler
import com.guichristovao.appstartup.network_support.Network
import com.guichristovao.appstartup.profile.ProfileFeature
import com.guichristovao.appstartup.profile.data.source.DefaultProfileRepository
import com.guichristovao.appstartup.profile.data.source.ProfileDataSource
import com.guichristovao.appstartup.profile.data.source.ProfileRepository
import com.guichristovao.appstartup.profile.data.source.remote.ProfileRemoteDataSource
import com.guichristovao.appstartup.profile.data.source.remote.ProfileService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(ActivityRetainedComponent::class)
object ProfileModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ProfileRemoteDataSource

    @Provides
    fun provideNetwork(): Network {
        return ProfileFeature.network
    }

    @Provides
    fun provideProfileService(network: Network): ProfileService {
        return network.createService(ProfileService::class.java)
    }

    @Provides
    fun provideExceptionHandler(network: Network): ExceptionHandler {
        return network::onFailure
    }

    @Provides
    @ProfileRemoteDataSource
    fun provideProfileRemoteDataSource(service: ProfileService): ProfileDataSource {
        return ProfileRemoteDataSource(service)
    }

    @Provides
    fun provideProfileRepository(
        @ProfileRemoteDataSource profileRemoteDataSource: ProfileDataSource
    ): ProfileRepository {
        return DefaultProfileRepository(profileRemoteDataSource)
    }

    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}
