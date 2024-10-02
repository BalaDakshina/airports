package com.example.lib_domain.repository

import com.example.lib_domain.ResultType
import com.example.lib_domain.model.AirPortDetail

interface AirPortDetailsRepository {
    suspend fun getAirportDetails(id: String): ResultType<AirPortDetail>
}