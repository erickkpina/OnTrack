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
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.epf1.ontrack.navigation.Screen
import com.epf1.ontrack.ui.components.BottomNavBar
import com.epf1.ontrack.ui.screens.DriverListScreen
import com.epf1.ontrack.ui.screens.StandingsScreen
import com.epf1.ontrack.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    companion object {
        val BACKGROUND_COLOR =  Color(0xFF1C1C1C)
        val TOP_BAR_COLOR = Color(0xF3262626)
        val BOTTOM_BAR_COLOR = Color(0xF3262626)
        val TITLE_COLOR = Color(0xFF313131)
        val TITLE_IN_DARK_BG = Color(0xFFEAEAEA)
        val DESCRIPTION_COLOR = Color(0xFF3D3D3D)
        val DESCRIPTION_IN_DARK_BG = Color(0xFFD0D0D0)
        val DIVIDER_COLOR = Color(0xFF2F2F2F)
        val SELECTION_COLOR = Color(0xFFFF0000)
    }

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

            Scaffold(
                bottomBar = { BottomNavBar(navController) }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Screen.Drivers.route,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(Screen.Drivers.route) {
                        DriverListScreen(viewModel = viewModel)
                    }
                    composable(Screen.Standings.route) {
                        StandingsScreen()
                    }
                    composable(Screen.Races.route) {
                        //TODO
                    }
                }
            }
        }
    }
}