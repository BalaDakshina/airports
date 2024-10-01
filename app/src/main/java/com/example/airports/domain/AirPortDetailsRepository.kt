package com.example.airports.domain

import com.example.airports.data.model.AirportDetail

interface AirPortDetailsRepository {
    suspend fun getAirportDetails(id: String): AirportDetail
}