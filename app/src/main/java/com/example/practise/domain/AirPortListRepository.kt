package com.example.practise.domain

import com.example.practise.data.model.AirPort

interface AirPortListRepository {
    suspend fun getUiData(): List<AirPort>
}