package com.example.lib_domain.usecases

import com.example.lib_domain.ResultType
import com.example.lib_domain.model.AirPortDetail
import com.example.lib_domain.repository.AirPortDetailsRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AirPortDetailsUseCaseTest {

    private val mockRepository = mockk<AirPortDetailsRepository>()
    private val subject = AirPortDetailsUseCase(mockRepository)

    @Test
    fun `GIVEN Success result type WHEN getAirPortDetails is called THEN detail is returned`() =
        runTest {
            val airPortId = "id"
            val detailsList = ResultType.Success(AirPortDetail())
            coEvery { mockRepository.getAirportDetails(airPortId) } returns detailsList

            val result = subject.invoke(airPortId)
            assertEquals(detailsList, result)
        }
}