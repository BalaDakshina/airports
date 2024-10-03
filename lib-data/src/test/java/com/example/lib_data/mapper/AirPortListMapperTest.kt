package com.example.lib_data.mapper

import com.example.lib_data.model.AirPortListResponse
import com.example.lib_data.model.AttributesResponse
import com.example.lib_data.model.DataResponse
import com.example.lib_data.model.LinksResponse
import com.example.lib_domain.ResultType
import com.example.lib_domain.model.AirPort
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AirPortListMapperTest {

    private val subject = AirPortListMapper()

    @Test
    fun `GIVEN AirPortListResponse WHEN map is called THEN returns list of AirPort`() {
        val response = ResultType.Success(
            AirPortListResponse(
                data = listOf(
                    DataResponse(
                        id = ID_1,
                        attributes = AttributesResponse(
                            country = COUNTRY,
                            city = CITY,
                            timezone = TIMEZONE,
                            name = NAME,
                            altitude = 10,
                            iata = IATA,
                            icao = ICAO,
                            latitude = LATITUDE,
                            longitude = LONGITUDE
                        ),
                        type = AIRPORT_TYPE
                    )
                ),
                links = LinksResponse(
                    self = LINK_SELF,
                    first = LINK_FIRST,
                    last = LINK_LAST,
                    next = LINK_NEXT,
                    prev = LINK_PREV
                ),
                id = ID_1
            )
        )
        val expected = ResultType.Success(
            listOf(
                AirPort(
                    id = ID_1,
                    name = NAME
                )
            )
        )

        val result = subject.map(response)

        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN AirPortListResponse with empty fields WHEN map is called THEN returns list of AirPort with empty fields`() {
        val response = ResultType.Success(
            AirPortListResponse(
                data = listOf(
                    DataResponse(
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
                ),
                links = LinksResponse(
                    self = EMPTY_STRING,
                    first = EMPTY_STRING,
                    last = EMPTY_STRING,
                    next = EMPTY_STRING,
                    prev = EMPTY_STRING
                ),
                id = EMPTY_STRING
            )
        )
        val expected = ResultType.Success(
            listOf(
                AirPort(
                    id = EMPTY_STRING,
                    name = EMPTY_STRING
                )
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
        const val IATA = "data"
        const val ICAO = "iconv"
        const val LATITUDE = "Latitude"
        const val LONGITUDE = "longitude"
        const val AIRPORT_TYPE = "airport"
        const val EMPTY_STRING = ""
        const val NAME = "Airport Name"
        const val LINK_SELF = "self"
        const val LINK_FIRST = "first"
        const val LINK_LAST = "last"
        const val LINK_NEXT = "next"
        const val LINK_PREV = "prev"
    }
}