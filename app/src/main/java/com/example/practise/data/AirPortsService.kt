package com.example.practise.data

import com.example.practise.data.model.AirPortDetailsResponse
import com.example.practise.data.model.AirportsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AirPortsService {
    @GET("airports")
    suspend fun getData(): Response<AirportsResponse>

    @GET("airports/{id}")
    suspend fun getAirportDetails(@Path("id") id: String): Response<AirPortDetailsResponse>
}