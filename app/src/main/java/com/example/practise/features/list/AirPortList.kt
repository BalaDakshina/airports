package com.example.practise.features.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.practise.data.model.AirPort
import com.example.practise.navigation.Screens.AirportDetails
import com.example.practise.ui.viewmodel.AirPortsListViewModel

@Composable
fun AirportListScreen(
    modifier: Modifier = Modifier,
    viewModel: AirPortsListViewModel = hiltViewModel(),
    navigator: NavController
) {
    val state: State<List<AirPort>> = viewModel.state.collectAsStateWithLifecycle()

    Column(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(state.value) {
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
}