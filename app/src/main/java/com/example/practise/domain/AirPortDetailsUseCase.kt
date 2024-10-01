package com.example.practise.domain

import com.example.practise.data.model.AirportDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AirPortDetailsUseCase @Inject constructor(
    private val airPortDetailsRepository: AirPortDetailsRepository
) {
    operator fun invoke(airPortId: String): Flow<AirportDetail> = flow {
        emit(airPortDetailsRepository.getAirportDetails(airPortId))
    }
}
