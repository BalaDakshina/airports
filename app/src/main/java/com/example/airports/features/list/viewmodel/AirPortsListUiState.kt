package com.example.airports.features.list.viewmodel

import com.example.airports.navigation.Screens
import com.example.lib_domain.model.AirPort

sealed class AirPortListUiState {
    data object Loading : AirPortListUiState()
    data class Success(val data: List<AirPort>) : AirPortListUiState()
    data object Error : AirPortListUiState()
}

sealed class ListScreenIntent {
    data class NavigateToDetail(val screen: Screens) : ListScreenIntent()
}

sealed class ListScreenEvent {
    data object OnInitialLoad : ListScreenEvent()
    data class OnAirportSelected(val airportId: String) : ListScreenEvent()
}