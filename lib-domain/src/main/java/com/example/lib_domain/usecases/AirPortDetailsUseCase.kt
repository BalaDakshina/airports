package com.example.lib_domain.usecases

import com.example.lib_domain.repository.AirPortDetailsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AirPortDetailsUseCase @Inject constructor(
    private val airPortDetailsRepository: AirPortDetailsRepository
) {
    operator fun invoke(airPortId: String) = flow {
        emit(airPortDetailsRepository.getAirportDetails(airPortId))
    }
}
