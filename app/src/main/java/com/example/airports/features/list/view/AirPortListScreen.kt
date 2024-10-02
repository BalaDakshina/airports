package com.example.airports.features.list.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.airports.composables.ErrorScreen
import com.example.airports.composables.LoadingScreen
import com.example.airports.features.list.viewmodel.AirPortListUiState
import com.example.airports.features.list.viewmodel.AirPortsListViewModel
import com.example.airports.features.list.viewmodel.UserEvent
import com.example.airports.features.list.viewmodel.UserIntent
import com.example.airports.ui.Dimensions
import com.example.lib_domain.model.AirPort

@Composable
fun AirPortListScreen(
    viewModel: AirPortsListViewModel = hiltViewModel(),
    navController: NavController
) {
    LaunchedEffect(Unit) {
        viewModel.userIntent.collect { userIntent ->
            when (userIntent) {
                is UserIntent.NavigateToDetail -> {
                    navController.navigate(userIntent.screen)
                }
            }
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    AirPortsContent(
        state = state,
        onAirportClick = viewModel::reduce
    )
}

@Composable
private fun AirPortsContent(
    state: AirPortListUiState,
    onAirportClick: (UserEvent) -> Unit
) {
    when (state) {
        is AirPortListUiState.Loading -> LoadingScreen()
        is AirPortListUiState.Success -> AirPortList(state.data, onAirportClick)
        is AirPortListUiState.Error -> ErrorScreen()
    }
}

@Composable
private fun AirPortList(
    airports: List<AirPort>,
    onAirportClick: (UserEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(Dimensions.mediumPadding)
    ) {
        items(airports) {
            Row(modifier = Modifier
                .padding(Dimensions.smallPadding)
                .fillMaxSize()
                .clickable {
                    onAirportClick(UserEvent.OnAirportSelected(it.id))
                }) {
                Text(
                    text = it.name,
                    modifier = Modifier
                        .padding(start = Dimensions.smallPadding)
                        .weight(1f)
                )
                Text(
                    text = it.id,
                    modifier = Modifier
                        .padding(start = Dimensions.smallPadding)
                )
            }
            HorizontalDivider()
        }
    }
}