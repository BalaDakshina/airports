package com.example.airports.data

import com.example.airports.data.model.AirPortDetailsResponse
import com.example.airports.data.model.AirportsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AirPortsService {
    @GET("airports")
    suspend fun getData(): Response<AirportsResponse>

    @GET("airports/{id}")
    suspend fun getAirportDetails(@Path("id") id: String): Response<AirPortDetailsResponse>
}