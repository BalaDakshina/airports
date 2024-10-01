package com.example.airports.injection

import com.example.airports.data.AirPortDetailsRepositoryImpl
import com.example.airports.data.AirPortListRepositoryImpl
import com.example.airports.data.AirPortsService
import com.example.airports.domain.AirPortDetailsRepository
import com.example.airports.domain.AirPortListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
class MainModule {

    @Provides
    @ViewModelScoped
    fun provideMainService(retrofit: Retrofit): AirPortsService {
        return retrofit.create<AirPortsService>()
    }

    @Provides
    @ViewModelScoped
    fun provideAirPortListRepository(airPortsService: AirPortsService): AirPortListRepository {
        return AirPortListRepositoryImpl(airPortsService)
    }

    @Provides
    @ViewModelScoped
    fun provideAirPortDetailsRepository(airPortsService: AirPortsService): AirPortDetailsRepository {
        return AirPortDetailsRepositoryImpl(airPortsService)
    }
}