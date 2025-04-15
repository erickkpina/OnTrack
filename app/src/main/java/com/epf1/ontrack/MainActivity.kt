package com.epf1.ontrack

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.epf1.ontrack.ui.screens.DriversStandingsScreen
import com.epf1.ontrack.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }

        setContent {
            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel()

            Scaffold(bottomBar = { BottomNavBar(navController) }) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Screen.Drivers.route,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(Screen.Drivers.route) {
                        DriverListScreen(viewModel = viewModel)
                    }
                    composable(Screen.DriversStandings.route) {
                        DriversStandingsScreen()
                    }
                    composable(Screen.Races.route) {
                        //TODO
                    }
                }
            }
        }
    }
}