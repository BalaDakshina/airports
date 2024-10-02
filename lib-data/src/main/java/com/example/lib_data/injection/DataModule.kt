package com.example.lib_data.injection

import com.example.lib_data.repository.AirPortDetailsRepositoryImpl
import com.example.lib_data.repository.AirPortListRepositoryImpl
import com.example.lib_domain.repository.AirPortDetailsRepository
import com.example.lib_domain.repository.AirPortListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsAirPortListRepository(airPortListRepositoryImpl: AirPortListRepositoryImpl): AirPortListRepository

    @Binds
    abstract fun bindsAirPortDetailsRepository(airPortDetailsRepositoryImpl: AirPortDetailsRepositoryImpl): AirPortDetailsRepository
}