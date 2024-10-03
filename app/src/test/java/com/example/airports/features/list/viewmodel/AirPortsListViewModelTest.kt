package com.example.airports.features.list.viewmodel

import app.cash.turbine.test
import com.example.airports.navigation.Screens
import com.example.airports.rule.MainDispatcherRule
import com.example.lib_domain.ResultType
import com.example.lib_domain.model.AirPort
import com.example.lib_domain.usecases.AirPortListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AirPortsListViewModelTest {
    @get: Rule
    val dispatcherRule = MainDispatcherRule()

    private val mockAirPortListUseCase = mockk<AirPortListUseCase>()
    private lateinit var subject: AirPortsListViewModel

    @Before
    fun setUp() {
        subject = AirPortsListViewModel(mockAirPortListUseCase)
    }

    @Test
    fun `GIVEN initial state WHEN ViewModel is created THEN state is Loading`() = runTest {
        coEvery { mockAirPortListUseCase() } returns flowOf(ResultType.Success(emptyList()))
        subject.state.test {
            assertEquals(AirPortListUiState.Loading, awaitItem())
        }
    }

    @Test
    fun `GIVEN use case returns success WHEN ViewModel is created THEN state is Success`() =
        runTest {
            val airPorts = listOf(AirPort("1", "Airport 1"))
            coEvery { mockAirPortListUseCase() } returns flowOf(ResultType.Success(airPorts))
            subject.state.test {
                awaitItem()
                assertEquals(AirPortListUiState.Success(airPorts), awaitItem())
            }
        }

    @Test
    fun `GIVEN use case returns error WHEN ViewModel is created THEN state is Error`() = runTest {
        coEvery { mockAirPortListUseCase() } returns flowOf(ResultType.Error(Exception("Error")))
        subject.state.test {
            awaitItem()
            assertEquals(AirPortListUiState.Error, awaitItem())
        }
    }

    @Test
    fun `GIVEN userEvent is OnAirportSelected WHEN reduce is called THEN userIntent is NavigateToDetail`() {
        runTest {
            val userEvent = ListScreenEvent.OnAirportSelected("1")
            subject.userIntent.test {
                subject.reduce(userEvent)
                assertEquals(
                    ListScreenIntent.NavigateToDetail(Screens.AirportDetails("1")),
                    awaitItem()
                )
            }
        }
    }
}