package com.example.airports.domain

import com.example.airports.data.model.AirPort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AirPortListUseCase @Inject constructor(
    private val airPortListRepository: AirPortListRepository
) {
    operator fun invoke(): Flow<ResultType<List<AirPort>>> = flow {
        emit(airPortListRepository.getUiData())
    }
}