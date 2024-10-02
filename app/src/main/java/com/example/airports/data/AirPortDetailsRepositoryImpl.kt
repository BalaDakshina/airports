package com.example.airports.data

import com.example.airports.data.model.AirportDetail
import com.example.airports.domain.AirPortDetailsRepository
import com.example.airports.domain.ResultType
import com.example.airports.domain.asResult
import com.example.airports.domain.safeApiCall
import javax.inject.Inject

class AirPortDetailsRepositoryImpl @Inject constructor(
    private val airportDetailsApi: AirPortsService
) : AirPortDetailsRepository {
    override suspend fun getAirportDetails(id: String): ResultType<AirportDetail> {
        val response = safeApiCall { airportDetailsApi.getAirportDetails(id) }
        return response.asResult {
            val attributes = it.data.attributes
            AirportDetail(
                id = it.data.id.orEmpty(),
                country = attributes.country,
                city = attributes.city,
                timeZone = attributes.timezone
            )
        }
    }
}