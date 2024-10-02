package com.example.lib_data.model

data class AirportsListResponse(
    val data: List<DataResponse>,
    val links: LinksResponse,
    val id: String,
)