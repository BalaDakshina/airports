package com.example.practise.injection

import com.example.practise.data.AirPortListRepositoryImpl
import com.example.practise.data.AirPortsService
import com.example.practise.domain.AirPortListRepository
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
}