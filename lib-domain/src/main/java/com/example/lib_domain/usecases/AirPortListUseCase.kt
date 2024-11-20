package com.example.lib_domain.usecases

import com.example.lib_domain.ResultType
import com.example.lib_domain.model.AirPort
import com.example.lib_domain.repository.AirPortListRepository
import javax.inject.Inject

class AirPortListUseCase @Inject constructor(
    private val airPortListRepository: AirPortListRepository
) {
    suspend operator fun invoke(): ResultType<List<AirPort>> =
        airPortListRepository.getAirPortList()
}