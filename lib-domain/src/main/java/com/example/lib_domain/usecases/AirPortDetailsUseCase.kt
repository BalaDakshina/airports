package com.example.lib_domain.usecases

import com.example.lib_domain.repository.AirPortDetailsRepository
import javax.inject.Inject

class AirPortDetailsUseCase @Inject constructor(
    private val airPortDetailsRepository: AirPortDetailsRepository
) {
    suspend operator fun invoke(airPortId: String) =
        airPortDetailsRepository.getAirportDetails(airPortId)
}