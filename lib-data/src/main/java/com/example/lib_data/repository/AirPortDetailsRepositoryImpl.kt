package com.example.lib_data.repository

import com.example.lib_data.mapper.AirPortDetailsMapper
import com.example.lib_data.model.AirPortDetailsResponse
import com.example.lib_data.services.AirPortsService
import com.example.lib_domain.ResultType
import com.example.lib_domain.model.AirPortDetail
import com.example.lib_domain.repository.AirPortDetailsRepository
import com.example.lib_domain.safeApiCall
import javax.inject.Inject

class AirPortDetailsRepositoryImpl @Inject constructor(
    private val airPortsService: AirPortsService,
    private val airPortDetailsMapper: AirPortDetailsMapper
) : AirPortDetailsRepository {

    override suspend fun getAirportDetails(id: String): ResultType<AirPortDetail> {
        val response: ResultType<AirPortDetailsResponse> = safeApiCall {
            airPortsService.getAirportDetails(id)
        }
        return airPortDetailsMapper.map(response)
    }
}