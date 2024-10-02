package com.example.airports.features.details.viewModel

import com.example.airports.data.model.AirportDetail

sealed class AirPortDetailsUiState {
    data object Loading : AirPortDetailsUiState()
    data class Success(val data: AirportDetail) : AirPortDetailsUiState()
    data object Error : AirPortDetailsUiState()
}