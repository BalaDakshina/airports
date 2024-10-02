package com.example.airports.features.list.viewmodel

import com.example.lib_domain.model.AirPort

sealed class AirPortListUiState {
    data object Loading : AirPortListUiState()
    data class Success(val data: List<AirPort>) : AirPortListUiState()
    data object Error : AirPortListUiState()
}