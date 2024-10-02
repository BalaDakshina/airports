package com.example.airports.features.details.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.airports.data.model.AirportDetail
import com.example.airports.domain.AirPortDetailsUseCase
import com.example.airports.domain.ResultType
import com.example.airports.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AirPortDetailsViewModel @Inject constructor(
    private val airPortDetailsUseCase: AirPortDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val state: StateFlow<AirPortDetailsUiState> by lazy {
        airPortDetailsUseCase(savedStateHandle.toRoute<Screens.AirportDetails>().id)
            .map { mapResult(it) }
            .catch { AirPortDetailsUiState.Error }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = AirPortDetailsUiState.Loading
            )
    }

    private fun mapResult(result: ResultType<AirportDetail>) =
        when (result) {
            is ResultType.Success -> AirPortDetailsUiState.Success(result.data)
            is ResultType.Error -> AirPortDetailsUiState.Error
        }
}