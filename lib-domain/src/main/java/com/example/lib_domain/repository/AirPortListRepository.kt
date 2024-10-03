package com.example.lib_domain.repository

import com.example.lib_domain.ResultType
import com.example.lib_domain.model.AirPort

interface AirPortListRepository {
    suspend fun getAirPortList(): ResultType<List<AirPort>>
}