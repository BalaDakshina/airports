package com.example.airports

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.airports.features.details.view.AirportDetailsScreen
import com.example.airports.features.list.view.AirportListScreen
import com.example.airports.navigation.Screens.AirportDetails
import com.example.airports.navigation.Screens.AirportList
import com.example.airports.ui.theme.AirportsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AirportsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AirportsApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AirportsApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(modifier = modifier, navController = navController, startDestination = AirportList) {
        composable<AirportList> { AirportListScreen(navigator = navController) }
        composable<AirportDetails> { AirportDetailsScreen() }
    }
}