package com.example.lib_data.repository

import com.example.lib_data.mapper.AirPortListMapper
import com.example.lib_data.services.AirPortsService
import com.example.lib_data.util.CoroutineDispatchersProvider
import com.example.lib_domain.ResultType
import com.example.lib_domain.model.AirPort
import com.example.lib_domain.repository.AirPortListRepository
import com.example.lib_domain.safeApiCall
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AirPortListRepositoryImpl @Inject constructor(
    private val airPortsService: AirPortsService,
    private val airPortsMapper: AirPortListMapper,
    private val dispatcher: CoroutineDispatchersProvider
) : AirPortListRepository {

    override suspend fun getAirPortList(): ResultType<List<AirPort>> {
        return withContext(dispatcher.default) {
            val response = safeApiCall {
                airPortsService.getAirportList()
            }
            airPortsMapper.map(response)
        }
    }
}