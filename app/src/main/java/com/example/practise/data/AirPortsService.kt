package com.example.practise.data

import com.example.practise.data.model.AirportsResponse
import retrofit2.Response
import retrofit2.http.GET

interface AirPortsService {
    @GET("airports")
    suspend fun getData(): Response<AirportsResponse>
}