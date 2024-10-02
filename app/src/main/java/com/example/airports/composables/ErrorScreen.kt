package com.example.airports.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.airports.R

@Composable
fun ErrorScreen() {
    Text(
        text = stringResource(R.string.couldn_t_load_data),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .wrapContentSize()
    )
}
