package com.example.practise.domain

import com.example.practise.data.model.AirPort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AirPortListUseCase @Inject constructor(
    private val airPortListRepository: AirPortListRepository
) {
    operator fun invoke(): Flow<List<AirPort>> = flow {
        emit(airPortListRepository.getUiData())
    }
}