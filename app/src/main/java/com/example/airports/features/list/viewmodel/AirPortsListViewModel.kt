package com.example.airports.features.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airports.navigation.Screens
import com.example.lib_domain.ResultType
import com.example.lib_domain.model.AirPort
import com.example.lib_domain.usecases.AirPortListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AirPortsListViewModel @Inject constructor(
    private val airPortListUseCase: AirPortListUseCase
) : ViewModel() {

    private val _userIntent = Channel<ListScreenIntent>()
    val userIntent = _userIntent.receiveAsFlow()

    private val _state = MutableStateFlow<AirPortListUiState>(AirPortListUiState.Loading)
    val state: StateFlow<AirPortListUiState> = _state

    fun reduce(userEvent: ListScreenEvent) {
        when (userEvent) {
            is ListScreenEvent.OnInitialLoad -> viewModelScope.launch { getAirPortDetails() }
            is ListScreenEvent.OnAirportSelected -> {
                _userIntent.trySend(
                    ListScreenIntent.NavigateToDetail(Screens.AirportDetails(userEvent.airportId))
                )
            }
        }
    }

    private suspend fun getAirPortDetails() {
        val result = airPortListUseCase()
        _state.value = mapResult(result)
    }

    private fun mapResult(result: ResultType<List<AirPort>>) =
        when (result) {
            is ResultType.Success -> AirPortListUiState.Success(result.data)
            is ResultType.Error -> AirPortListUiState.Error
        }
}