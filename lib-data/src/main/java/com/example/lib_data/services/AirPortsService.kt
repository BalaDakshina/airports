package com.example.lib_data.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AirPortsService {
    @GET("airports")
    suspend fun getData(): Response<com.example.lib_data.model.AirPortListResponse>

    @GET("airports/{id}")
    suspend fun getAirportDetails(@Path("id") id: String): Response<com.example.lib_data.model.AirPortDetailsResponse>
}