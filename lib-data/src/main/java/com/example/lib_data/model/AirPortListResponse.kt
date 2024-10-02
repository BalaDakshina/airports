package com.example.lib_data.model

data class AirPortListResponse(
    val data: List<DataResponse>,
    val links: LinksResponse,
    val id: String,
)