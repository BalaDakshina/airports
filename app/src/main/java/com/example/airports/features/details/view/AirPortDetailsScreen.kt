package com.example.airports.features.details.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.airports.R
import com.example.airports.composables.ErrorScreen
import com.example.airports.composables.LoadingScreen
import com.example.airports.features.details.viewModel.AirPortDetailsUiState
import com.example.airports.features.details.viewModel.AirPortDetailsViewModel
import com.example.airports.features.details.viewModel.DetailsScreenEvent
import com.example.airports.ui.AirportsTheme
import com.example.airports.ui.Dimensions.fontSizeMedium
import com.example.airports.ui.Dimensions.mediumPadding
import com.example.lib_domain.model.AirPortDetail

@Composable
fun AirPortDetailsScreen(viewModel: AirPortDetailsViewModel = hiltViewModel()) {
    val isInitialLoad = rememberSaveable { mutableStateOf(true) }
    if (isInitialLoad.value) {
        LaunchedEffect(Unit) {
            viewModel.reduce(DetailsScreenEvent.OnInitialLoad)
        }
        isInitialLoad.value = false
    }

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

class AirPortDetailsPreviewParameterProvider : PreviewParameterProvider<AirPortDetailsUiState> {
    override val values: Sequence<AirPortDetailsUiState> = sequenceOf(
        AirPortDetailsUiState.Loading,
        AirPortDetailsUiState.Success(
            AirPortDetail(id = "1", country = "UK", city = "London", timeZone = "GMT")
        ),
        AirPortDetailsUiState.Error
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAirPortListScreen(
    @PreviewParameter(AirPortDetailsPreviewParameterProvider::class) airPortDetailsUiState: AirPortDetailsUiState
) {
    AirportsTheme {
        AirPortDetailContent(state = airPortDetailsUiState)
    }
}
