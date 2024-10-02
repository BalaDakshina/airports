package com.example.lib_data.repository

import com.example.lib_data.services.AirPortsService
import com.example.lib_domain.ResultType
import com.example.lib_domain.asResult
import com.example.lib_domain.model.AirportDetail
import com.example.lib_domain.repository.AirPortDetailsRepository
import com.example.lib_domain.safeApiCall
import javax.inject.Inject

class AirPortDetailsRepositoryImpl @Inject constructor(
    private val airPortsService: AirPortsService
) : AirPortDetailsRepository {

    override suspend fun getAirportDetails(id: String): ResultType<AirportDetail> {
        val response = safeApiCall { airPortsService.getAirportDetails(id) }
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