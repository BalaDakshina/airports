package com.example.airports.navigation

import kotlinx.serialization.Serializable

class Screens {
    @Serializable
    data object AirportList

    @Serializable
    data class AirportDetails(val id: String)
}