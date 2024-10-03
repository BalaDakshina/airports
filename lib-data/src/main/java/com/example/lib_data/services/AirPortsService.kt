package com.example.lib_data.services

import com.example.lib_data.model.AirPortDetailsResponse
import com.example.lib_data.model.AirPortListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AirPortsService {
    @GET("airports")
    suspend fun getAirportList(): Response<AirPortListResponse>

    @GET("airports/{id}")
    suspend fun getAirportDetails(@Path("id") id: String): Response<AirPortDetailsResponse>
}