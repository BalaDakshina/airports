package com.example.practise.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideHttpClient(): OkHttpClient {
        val client = OkHttpClient().newBuilder()
        return client.build()
    }

    @Provides
    fun provideRetrofit(okhttp: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://airportgap.com/api/")
            .client(okhttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}