package com.example.lib_data.model

data class AirPortDetailsResponse(
    val data: DataResponse
)

data class DataResponse(
    val attributes: AttributesResponse,
    val id: String?,
    val type: String?
)

data class AttributesResponse(
    val altitude: Int,
    val city: String,
    val country: String,
    val iata: String,
    val icao: String,
    val latitude: String,
    val longitude: String,
    val name: String,
    val timezone: String
)


data class LinksResponse(
    val first: String,
    val last: String,
    val next: String,
    val prev: String,
    val self: String
)