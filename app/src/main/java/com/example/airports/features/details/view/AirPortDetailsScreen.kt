package com.example.airports.features.details.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.airports.R
import com.example.airports.composables.ErrorScreen
import com.example.airports.composables.LoadingScreen
import com.example.airports.features.details.viewModel.AirPortDetailsUiState
import com.example.airports.features.details.viewModel.AirPortDetailsViewModel
import com.example.airports.ui.Dimensions.fontSizeMedium
import com.example.airports.ui.Dimensions.mediumPadding
import com.example.lib_domain.model.AirPortDetail

@Composable
fun AirPortDetailsScreen(viewModel: AirPortDetailsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    AirPortDetailContent(state = state)
}

@Composable
fun AirPortDetailContent(state: AirPortDetailsUiState) {
    when (state) {
        is AirPortDetailsUiState.Success -> AirPortDetail(state.data)
        is AirPortDetailsUiState.Error -> ErrorScreen()
        is AirPortDetailsUiState.Loading -> LoadingScreen()
    }
}

@Composable
private fun AirPortDetail(airportDetail: AirPortDetail) {
    Column(modifier = Modifier.padding(mediumPadding)) {
        Text(
            text = stringResource(R.string.airport_details),
            fontSize = fontSizeMedium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        HorizontalDivider(modifier = Modifier.padding(mediumPadding))
        Text(text = stringResource(R.string.country, airportDetail.country))
        Text(text = stringResource(R.string.city, airportDetail.city))
        Text(text = stringResource(R.string.timezone, airportDetail.timeZone))
        HorizontalDivider(modifier = Modifier.padding(mediumPadding))
    }
}
