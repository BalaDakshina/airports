package com.example.lib_data.mapper

import com.example.lib_data.model.AirPortListResponse
import com.example.lib_domain.ResultType
import com.example.lib_domain.asResult
import com.example.lib_domain.model.AirPort
import javax.inject.Inject

class AirPortListMapper @Inject constructor() {
    fun map(response: ResultType<AirPortListResponse>): ResultType<List<AirPort>> =
        response.asResult { result ->
            result.data.map {
                AirPort(
                    name = it.attributes.name,
                    id = it.id.orEmpty()
                )
            }
        }
}