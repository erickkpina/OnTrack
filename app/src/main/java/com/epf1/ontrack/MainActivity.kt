package com.epf1.ontrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.epf1.ontrack.navigation.Screen
import com.epf1.ontrack.ui.components.BottomNavBar
import com.epf1.ontrack.ui.screens.DriverListScreen
import com.epf1.ontrack.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel()
            /*DriverListScreen(viewModel = viewModel)*/

            Scaffold(bottomBar = { BottomNavBar(navController) }) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Screen.Drivers.route,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(Screen.Drivers.route) {
                        DriverListScreen(viewModel = viewModel)
                    }
                    composable(Screen.Teams.route) {
                        //TODO
                    }
                    composable(Screen.Races.route) {
                        //TODO
                    }
                }
            }
        }
    }
}