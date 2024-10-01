package com.example.practise.features.details

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
import com.example.practise.ui.viewmodel.AirPortDetailsViewModel

@Composable
fun AirportDetailsScreen(viewModel: AirPortDetailsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Airport Details", modifier = Modifier.align(Alignment.CenterHorizontally))
        HorizontalDivider(modifier = Modifier.padding(4.dp))
        Text(text = "Country : ${state.country}")
        Text(text = "City : ${state.city}")
        Text(text = "TimeZone : ${state.timeZone}")
        HorizontalDivider(modifier = Modifier.padding(4.dp))
    }
}
