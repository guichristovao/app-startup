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
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Provides
    fun provideNetwork(): Network {
        return ProfileFeature.network
    }

    @Provides
    fun provideExceptionHandler(network: Network): ExceptionHandler {
        return network::onFailure
    }
}

@Module
@InstallIn(ViewModelComponent::class)
object ProfileDataSourceModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ProfileRemoteDataSource

    @Provides
    fun provideProfileService(network: Network): ProfileService {
        return network.createService(ProfileService::class.java)
    }

    @Provides
    @ProfileRemoteDataSource
    fun provideProfileRemoteDataSource(
        service: ProfileService,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): ProfileDataSource {
        return ProfileRemoteDataSource(service, ioDispatcher)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
object ProfileRepositoryModule {

    @Provides
    fun provideProfileRepository(
        @ProfileDataSourceModule.ProfileRemoteDataSource profileRemoteDataSource: ProfileDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): ProfileRepository {
        return DefaultProfileRepository(profileRemoteDataSource, ioDispatcher)
    }
}
