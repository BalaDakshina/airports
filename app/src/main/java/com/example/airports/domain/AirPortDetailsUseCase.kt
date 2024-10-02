package com.example.airports.domain

import com.example.airports.data.model.AirportDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AirPortDetailsUseCase @Inject constructor(
    private val airPortDetailsRepository: AirPortDetailsRepository
) {
    operator fun invoke(airPortId: String): Flow<ResultType<AirportDetail>> = flow {
        emit(airPortDetailsRepository.getAirportDetails(airPortId))
    }
}
