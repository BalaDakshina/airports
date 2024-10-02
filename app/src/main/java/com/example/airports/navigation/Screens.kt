package com.example.airports.navigation

import kotlinx.serialization.Serializable

sealed class Screens {
    @Serializable
    data object AirportList : Screens()

    @Serializable
    data class AirportDetails(val id: String) : Screens()
}