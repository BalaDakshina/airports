package com.example.lib_data.repository

import com.example.lib_data.mapper.AirPortListMapper
import com.example.lib_data.model.AirPortListResponse
import com.example.lib_data.services.AirPortsService
import com.example.lib_domain.ResultType
import com.example.lib_domain.model.AirPort
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class AirPortListRepositoryImplTest {
    private val mockService = mockk<AirPortsService>()
    private val mockMapper = mockk<AirPortListMapper>()
    private val subject = AirPortListRepositoryImpl(mockService, mockMapper)

    @Test
    fun `GIVEN successful API call WHEN getAirPortList is called THEN list is returned`() =
        runTest {
            val mockListsResponse = mockk<AirPortListResponse>()
            val mockResponse = Response.success(mockListsResponse)
            val mockResult = ResultType.Success(mockListsResponse)
            val mockList = mockk<AirPort>()
            coEvery { mockService.getAirportList() } returns mockResponse
            every { mockMapper.map(mockResult) } returns ResultType.Success(listOf(mockList))

            val result = subject.getAirPortList()

            assertEquals(ResultType.Success(listOf(mockList)), result)
            coVerify {
                mockService.getAirportList()
                mockMapper.map(mockResult)
            }
        }

    @Test
    fun `GIVEN un-successful API call WHEN getAirportList is called THEN error is returned`() =
        runTest {
            val exception = Exception()
            val mockResult = ResultType.Error(exception)

            coEvery { mockService.getAirportList() } throws exception
            every { mockMapper.map(mockResult) } returns ResultType.Error(exception)

            val result = subject.getAirPortList()

            assertEquals(ResultType.Error(exception), result)
            coVerify {
                mockService.getAirportList()
                mockMapper.map(mockResult)
            }
        }
}