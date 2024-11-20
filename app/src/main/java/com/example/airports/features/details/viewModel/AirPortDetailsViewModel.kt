package com.example.airports.features.details.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.airports.navigation.Screens
import com.example.lib_domain.ResultType
import com.example.lib_domain.ResultType.Error
import com.example.lib_domain.ResultType.Success
import com.example.lib_domain.model.AirPortDetail
import com.example.lib_domain.usecases.AirPortDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AirPortDetailsViewModel @Inject constructor(
    private val airPortDetailsUseCase: AirPortDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow<AirPortDetailsUiState>(AirPortDetailsUiState.Loading)
    val state: StateFlow<AirPortDetailsUiState> = _state

    fun reduce(intent: DetailsScreenEvent) {
        when (intent) {
            is DetailsScreenEvent.OnInitialLoad -> viewModelScope.launch {
                getAirPortDetails()
            }
        }
    }

    private suspend fun getAirPortDetails() {
        val airPortId = savedStateHandle.toRoute<Screens.AirportDetails>().id
        val result = airPortDetailsUseCase(airPortId)
        _state.value = mapResult(result)
    }

    private fun mapResult(result: ResultType<AirPortDetail>) =
        when (result) {
            is Success -> AirPortDetailsUiState.Success(result.data)
            is Error -> AirPortDetailsUiState.Error
        }
}