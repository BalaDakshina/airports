package com.example.lib_data.repository

import com.example.lib_data.mapper.AirPortListMapper
import com.example.lib_data.model.AirPortListResponse
import com.example.lib_data.services.AirPortsService
import com.example.lib_domain.ResultType
import com.example.lib_domain.model.AirPort
import com.example.lib_domain.repository.AirPortListRepository
import com.example.lib_domain.safeApiCall
import javax.inject.Inject

class AirPortListRepositoryImpl @Inject constructor(
    private val airPortsService: AirPortsService,
    private val airPortsMapper: AirPortListMapper
) : AirPortListRepository {

    override suspend fun getAirPortList(): ResultType<List<AirPort>> {
        val response: ResultType<AirPortListResponse> =
            safeApiCall { airPortsService.getAirportList() }
        return airPortsMapper.map(response)
    }
}