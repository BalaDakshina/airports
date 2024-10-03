package com.example.lib_data.repository

import com.example.lib_data.mapper.AirPortDetailsMapper
import com.example.lib_data.model.AirPortDetailsResponse
import com.example.lib_data.services.AirPortsService
import com.example.lib_domain.ResultType
import com.example.lib_domain.model.AirPortDetail
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class DetailsRepositoryImplTest {

    private val mockService = mockk<AirPortsService>()
    private val mockMapper = mockk<AirPortDetailsMapper>()
    private val subject = AirPortDetailsRepositoryImpl(mockService, mockMapper)

    @Test
    fun `GIVEN successful API call WHEN getUserDetails is called THEN details list is returned`() =
        runTest {
            val mockDetailsResponse = mockk<AirPortDetailsResponse>()
            val mockResponse = Response.success(mockDetailsResponse)
            val mockResult = ResultType.Success(mockDetailsResponse)
            val mockDetails = mockk<AirPortDetail>()
            coEvery { mockService.getAirportDetails(AIRPORT_ID) } returns mockResponse
            every { mockMapper.map(mockResult) } returns ResultType.Success(mockDetails)

            val result = subject.getAirportDetails(AIRPORT_ID)

            assertEquals(ResultType.Success(mockDetails), result)
            coVerify {
                mockService.getAirportDetails(AIRPORT_ID)
                mockMapper.map(mockResult)
            }
        }

    @Test
    fun `GIVEN un-successful API call WHEN getUserDetails is called THEN error is returned`() =
        runTest {
            val exception = Exception()
            val mockResult = ResultType.Error(exception)

            coEvery { mockService.getAirportDetails(AIRPORT_ID) } throws exception
            every { mockMapper.map(mockResult) } returns ResultType.Error(exception)

            val result = subject.getAirportDetails(AIRPORT_ID)

            assertEquals(ResultType.Error(exception), result)
            coVerify {
                mockService.getAirportDetails(AIRPORT_ID)
                mockMapper.map(mockResult)
            }
        }

    companion object {
        private const val AIRPORT_ID = "userId"
    }
}