package com.example.lib_domain.usecases

import com.example.lib_domain.ResultType
import com.example.lib_domain.model.AirPort
import com.example.lib_domain.repository.AirPortListRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetUserListUseCaseTest {

    private val mockRepository = mockk<AirPortListRepository>()
    private val subject = AirPortListUseCase(mockRepository)

    @Test
    fun `GIVEN Success result type WHEN getAirPortsList is called THEN airport list is returned`() =
        runTest {
            val airPortResult = ResultType.Success(emptyList<AirPort>())
            coEvery { mockRepository.getAirPortList() } returns airPortResult

            subject.invoke().collectLatest {
                assertEquals(airPortResult, it)
            }
        }
}