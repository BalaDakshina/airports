package com.example.airports.features.details.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.airports.composables.ErrorScreen
import com.example.airports.composables.LoadingScreen
import com.example.airports.features.details.viewModel.AirPortDetailsUiState
import com.example.airports.features.details.viewModel.AirPortDetailsViewModel
import com.example.lib_domain.model.AirportDetail

@Composable
fun AirportDetailsScreen(viewModel: AirPortDetailsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    AirportDetailContent(state)
}

@Composable
fun AirportDetailContent(state: AirPortDetailsUiState) {
    when (state) {
        is AirPortDetailsUiState.Success -> AirportDetail(state.data)
        is AirPortDetailsUiState.Error -> ErrorScreen()
        is AirPortDetailsUiState.Loading -> LoadingScreen()
    }
}

@Composable
private fun AirportDetail(airportDetail: AirportDetail) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Airport Details",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        HorizontalDivider(modifier = Modifier.padding(4.dp))
        Text(text = "Country : ${airportDetail.country}")
        Text(text = "City : ${airportDetail.city}")
        Text(text = "TimeZone : ${airportDetail.timeZone}")
        HorizontalDivider(modifier = Modifier.padding(4.dp))
    }
}
