package com.example.lib_domain.repository

import com.example.lib_domain.ResultType
import com.example.lib_domain.model.AirPort

interface AirPortListRepository {
    suspend fun getUiData(): ResultType<List<AirPort>>
}