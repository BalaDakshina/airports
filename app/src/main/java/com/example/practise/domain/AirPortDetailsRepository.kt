package com.example.practise.domain

import com.example.practise.data.model.AirportDetail

interface AirPortDetailsRepository {
    suspend fun getAirportDetails(id: String): AirportDetail
}