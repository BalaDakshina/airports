package com.example.lib_data.mapper

import com.example.lib_data.model.AirPortDetailsResponse
import com.example.lib_data.model.AttributesResponse
import com.example.lib_data.model.DataResponse
import com.example.lib_domain.ResultType
import com.example.lib_domain.model.AirPortDetail
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AirPortDetailsMapperTest {

    private val subject = AirPortDetailsMapper()

    @Test
    fun `GIVEN AirPortDetailsResponse WHEN map is called THEN returns AirPortDetail`() {
        val response = ResultType.Success(
            AirPortDetailsResponse(
                data = DataResponse(
                    id = ID_1,
                    attributes = AttributesResponse(
                        country = COUNTRY,
                        city = CITY,
                        timezone = TIMEZONE,
                        name = AIRPORT_NAME,
                        altitude = 10,
                        iata = IATA,
                        icao = ICAO,
                        latitude = LATITUDE,
                        longitude = LONGITUDE
                    ),
                    type = AIRPORT_TYPE
                )
            )
        )
        val expected = ResultType.Success(
            AirPortDetail(
                id = ID_1,
                country = COUNTRY,
                city = CITY,
                timeZone = TIMEZONE
            )
        )

        val result = subject.map(response)

        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN AirPortDetailsResponse with empty fields WHEN map is called THEN returns AirPortDetail with empty fields`() {
        val response = ResultType.Success(
            AirPortDetailsResponse(
                data = DataResponse(
                    id = EMPTY_STRING,
                    attributes = AttributesResponse(
                        country = EMPTY_STRING,
                        city = EMPTY_STRING,
                        timezone = EMPTY_STRING,
                        name = EMPTY_STRING,
                        altitude = 10,
                        iata = EMPTY_STRING,
                        icao = EMPTY_STRING,
                        latitude = EMPTY_STRING,
                        longitude = EMPTY_STRING
                    ),
                    type = AIRPORT_TYPE
                )
            )
        )
        val expected = ResultType.Success(
            AirPortDetail(
                id = EMPTY_STRING,
                country = EMPTY_STRING,
                city = EMPTY_STRING,
                timeZone = EMPTY_STRING
            )
        )

        val result = subject.map(response)

        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN error response WHEN map is called THEN returns empty list`() {
        val response = ResultType.Error(Exception("Network error"))

        val result = subject.map(response)

        assertTrue(result is ResultType.Error)
    }

    companion object {
        const val ID_1 = "1"
        const val COUNTRY = "Country"
        const val CITY = "City"
        const val TIMEZONE = "Timezone"
        const val AIRPORT_NAME = "Airport Name"
        const val IATA = "data"
        const val ICAO = "iconv"
        const val LATITUDE = "Latitude"
        const val LONGITUDE = "longitude"
        const val AIRPORT_TYPE = "airport"
        const val EMPTY_STRING = ""
    }
}