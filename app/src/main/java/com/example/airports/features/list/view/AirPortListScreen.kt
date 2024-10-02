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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.airports.composables.ErrorScreen
import com.example.airports.composables.LoadingScreen
import com.example.airports.data.model.AirPort
import com.example.airports.features.list.viewmodel.AirPortListUiState
import com.example.airports.features.list.viewmodel.AirPortsListViewModel
import com.example.airports.navigation.Screens.AirportDetails

@Composable
fun AirportListScreen(
    viewModel: AirPortsListViewModel = hiltViewModel(),
    navigator: NavController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    AirPortsContent(state, navigator)
}

@Composable
private fun AirPortsContent(
    state: AirPortListUiState,
    navigator: NavController
) {
    when (state) {
        is AirPortListUiState.Loading -> LoadingScreen()
        is AirPortListUiState.Success -> AirPortList(state.data, navigator = navigator)
        is AirPortListUiState.Error -> ErrorScreen()
    }
}

@Composable
private fun AirPortList(
    airports: List<AirPort>,
    navigator: NavController
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(airports) {
            Row(modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
                .clickable {
                    navigator.navigate(AirportDetails(it.id))
                }) {
                Text(
                    text = it.name, modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(1f)
                )
                Text(
                    text = it.id, modifier = Modifier
                        .padding(start = 8.dp)
                )
            }
            HorizontalDivider()
        }
    }
}