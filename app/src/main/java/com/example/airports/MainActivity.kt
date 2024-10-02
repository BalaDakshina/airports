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
import com.example.airports.features.details.view.AirPortDetailsScreen
import com.example.airports.features.list.view.AirPortListScreen
import com.example.airports.navigation.Screens.AirportDetails
import com.example.airports.navigation.Screens.AirportList
import com.example.airports.ui.AirportsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AirportsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AirPortsApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AirPortsApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(modifier = modifier, navController = navController, startDestination = AirportList) {
        composable<AirportList> { AirPortListScreen(navController = navController) }
        composable<AirportDetails> { AirPortDetailsScreen() }
    }
}