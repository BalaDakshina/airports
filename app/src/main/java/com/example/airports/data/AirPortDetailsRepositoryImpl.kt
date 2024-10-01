package com.example.airports.data

import com.example.airports.data.model.AirportDetail
import com.example.airports.domain.AirPortDetailsRepository
import javax.inject.Inject

class AirPortDetailsRepositoryImpl @Inject constructor(
    private val airportDetailsApi: AirPortsService
) : AirPortDetailsRepository {
    override suspend fun getAirportDetails(id: String): AirportDetail {
        val response = airportDetailsApi.getAirportDetails(id)
        if (response.isSuccessful) {
            return response.body()?.let {
                val attributes = it.data.attributes
                AirportDetail(
                    id = it.data.id.orEmpty(),
                    country = attributes.country,
                    city = attributes.city,
                    timeZone = attributes.timezone
                )
            } ?: throw Exception("No data found")
        } else {
            throw Exception("No data found")
        }
    }
}