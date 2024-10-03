package com.example.airports.features.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import app.cash.turbine.test
import com.example.airports.features.details.viewModel.AirPortDetailsUiState
import com.example.airports.features.details.viewModel.AirPortDetailsViewModel
import com.example.airports.navigation.Screens
import com.example.airports.rule.MainDispatcherRule
import com.example.lib_domain.ResultType
import com.example.lib_domain.model.AirPortDetail
import com.example.lib_domain.usecases.AirPortDetailsUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val AIRPORT_ID = "1"
private const val AIRPORT_NAME = "Airport 1"

@OptIn(ExperimentalCoroutinesApi::class)
class AirPortDetailsViewModelTest {

    @get: Rule
    val dispatcherRule = MainDispatcherRule()

    private val mockAirPortDetailsUseCase = mockk<AirPortDetailsUseCase>()
    private val savedStateHandle = mockk<SavedStateHandle>()

    private lateinit var subject: AirPortDetailsViewModel


    @Before
    fun setUp() {
        mockkStatic("androidx.navigation.SavedStateHandleKt")
        every { savedStateHandle.toRoute<Screens.AirportDetails>() } returns Screens.AirportDetails(
            id = AIRPORT_ID
        )
        subject = AirPortDetailsViewModel(mockAirPortDetailsUseCase, savedStateHandle)
    }

    @Test
    fun `GIVEN initial state WHEN ViewModel is created THEN state is Loading`() = runTest {
        coEvery { mockAirPortDetailsUseCase(any()) } returns flowOf(
            ResultType.Success(AirPortDetail(AIRPORT_ID, AIRPORT_NAME))
        )
        subject.state.test {
            assertEquals(AirPortDetailsUiState.Loading, awaitItem())
        }
    }

    @Test
    fun `GIVEN use case returns success WHEN ViewModel is created THEN state is Success`() =
        runTest {
            val airPortDetail = AirPortDetail(AIRPORT_ID, AIRPORT_NAME)
            coEvery { mockAirPortDetailsUseCase(any()) } returns flowOf(
                ResultType.Success(airPortDetail)
            )
            subject.state.test {
                awaitItem()
                assertEquals(AirPortDetailsUiState.Success(airPortDetail), awaitItem())
            }
        }

    @Test
    fun `GIVEN use case returns error WHEN ViewModel is created THEN state is Error`() = runTest {
        coEvery { mockAirPortDetailsUseCase(any()) } returns flowOf(ResultType.Error(Exception("Error")))
        subject.state.test {
            awaitItem()
            assertEquals(AirPortDetailsUiState.Error, awaitItem())
        }
    }
}