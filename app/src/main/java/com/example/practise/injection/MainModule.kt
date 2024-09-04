package com.example.practise.injection

import com.example.practise.data.MainService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
class MainModule {

    @Provides
    fun provideMainService(retrofit: Retrofit): MainService {
        return retrofit.create<MainService>()
    }
}