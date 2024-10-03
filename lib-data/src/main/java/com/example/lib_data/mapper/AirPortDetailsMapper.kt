package com.example.lib_data.mapper

import com.example.lib_data.model.AirPortDetailsResponse
import com.example.lib_domain.ResultType
import com.example.lib_domain.asResult
import com.example.lib_domain.model.AirPortDetail
import javax.inject.Inject

class AirPortDetailsMapper @Inject constructor() {
    fun map(response: ResultType<AirPortDetailsResponse>): ResultType<AirPortDetail> =
        response.asResult { response ->
            val attributes = response.data.attributes
            AirPortDetail(
                id = response.data.id.orEmpty(),
                country = attributes.country,
                city = attributes.city,
                timeZone = attributes.timezone
            )
        }
}
