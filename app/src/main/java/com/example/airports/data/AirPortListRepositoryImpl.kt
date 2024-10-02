package com.example.airports.data

import com.example.airports.data.model.AirPort
import com.example.airports.data.model.AirportsResponse
import com.example.airports.domain.AirPortListRepository
import com.example.airports.domain.ResultType
import com.example.airports.domain.asResult
import com.example.airports.domain.safeApiCall
import javax.inject.Inject

class AirPortListRepositoryImpl @Inject constructor(
    private val airPortsService: AirPortsService
) : AirPortListRepository {
    override suspend fun getUiData(): ResultType<List<AirPort>> {
        val response: ResultType<AirportsResponse> = safeApiCall { airPortsService.getData() }
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