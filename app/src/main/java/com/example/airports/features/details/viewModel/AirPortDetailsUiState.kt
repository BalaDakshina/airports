package com.example.airports.features.details.viewModel

import com.example.lib_domain.model.AirPortDetail

sealed class AirPortDetailsUiState {
    data object Loading : AirPortDetailsUiState()
    data class Success(val data: AirPortDetail) : AirPortDetailsUiState()
    data object Error : AirPortDetailsUiState()
}