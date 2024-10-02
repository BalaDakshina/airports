package com.example.airports.domain

import com.example.airports.data.model.AirPort

interface AirPortListRepository {
    suspend fun getUiData(): ResultType<List<AirPort>>
}