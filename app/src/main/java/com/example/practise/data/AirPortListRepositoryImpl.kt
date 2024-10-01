package com.example.practise.data

import android.util.Log
import com.example.practise.data.model.AirPort
import com.example.practise.domain.AirPortListRepository
import javax.inject.Inject

class AirPortListRepositoryImpl @Inject constructor(
    private val airPortsService: AirPortsService
) : AirPortListRepository {
    override suspend fun getUiData(): List<AirPort> {
        val response = airPortsService.getData()
        if (response.isSuccessful) {
            return response.body()?.data?.map {
                AirPort(
                    name = it.attributes.name,
                    id = it.id
                )
            } ?: emptyList()
        } else {
            Log.d("TAGFPM", "getUiData: ${response.errorBody()}")
            return listOf(AirPort("call failed", "0"))
        }
    }
}