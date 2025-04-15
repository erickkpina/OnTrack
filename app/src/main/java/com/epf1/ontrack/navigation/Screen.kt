package com.epf1.ontrack.navigation

sealed class Screen(val route: String, val label: String) {
    object Drivers : Screen("drivers", "Drivers")
    object DriversStandings : Screen("driversStandings", "DriversStandings")
    object Races : Screen("races", "Races")
}