package com.example.airports.data.model

data class AirportsResponse(
    val `data`: List<Data>,
    val links: Links,
    val id: String,
)