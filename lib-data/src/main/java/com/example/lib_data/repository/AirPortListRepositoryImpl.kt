package com.example.lib_data.repository

import com.example.lib_data.model.AirportsListResponse
import com.example.lib_data.services.AirPortsService
import com.example.lib_domain.ResultType
import com.example.lib_domain.asResult
import com.example.lib_domain.model.AirPort
import com.example.lib_domain.repository.AirPortListRepository
import com.example.lib_domain.safeApiCall
import javax.inject.Inject

class AirPortListRepositoryImpl @Inject constructor(
    private val airPortsService: AirPortsService
) : AirPortListRepository {
    override suspend fun getUiData(): ResultType<List<AirPort>> {
        val response: ResultType<AirportsListResponse> =
            safeApiCall { airPortsService.getData() }
        return response.asResult { result ->
            result.data.map {
                AirPort(
                    name = it.attributes.name,
                    id = it.id.orEmpty()
                )
            }
        }
    }
}