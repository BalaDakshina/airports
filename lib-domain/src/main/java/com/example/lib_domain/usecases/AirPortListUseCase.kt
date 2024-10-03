package com.example.lib_domain.usecases

import com.example.lib_domain.ResultType
import com.example.lib_domain.model.AirPort
import com.example.lib_domain.repository.AirPortListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AirPortListUseCase @Inject constructor(
    private val airPortListRepository: AirPortListRepository
) {
    operator fun invoke(): Flow<ResultType<List<AirPort>>> = flow {
        emit(airPortListRepository.getAirPortList())
    }
}