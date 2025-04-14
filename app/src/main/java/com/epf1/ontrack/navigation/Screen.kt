package com.epf1.ontrack.navigation

sealed class Screen(val route: String, val label: String) {
    object Drivers : Screen("drivers", "Drivers")
    object Teams : Screen("teams", "Teams")
    object Races : Screen("races", "Races")
}