package com.example.lib_data.mapper

import com.example.lib_data.model.AirPortDetailsResponse
import com.example.lib_domain.model.AirPortDetail
import javax.inject.Inject

class AirPortDetailsMapper @Inject constructor() {
    fun map(it: AirPortDetailsResponse): AirPortDetail {
        val attributes = it.data.attributes
        return AirPortDetail(
            id = it.data.id.orEmpty(),
            country = attributes.country,
            city = attributes.city,
            timeZone = attributes.timezone
        )
    }
}
