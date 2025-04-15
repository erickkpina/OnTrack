package com.epf1.ontrack.ui.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.epf1.ontrack.navigation.Screen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import com.epf1.ontrack.MainActivity

data class BottomNavItem(val screen: Screen, val icon: ImageVector)

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem(Screen.Drivers, Icons.Filled.Person),
        BottomNavItem(Screen.Standings, Icons.Filled.Star),
        BottomNavItem(Screen.Races, Icons.Filled.ShoppingCart)
    )

    BottomNavigation(backgroundColor = MainActivity.BOTTOM_BAR_COLOR) {
        val navBackStackEntry = navController.currentBackStackEntryAsState().value
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.screen.label, tint = MainActivity.TITLE_IN_DARK_BG) },
                label = { Text(item.screen.label, color = MainActivity.TITLE_IN_DARK_BG) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    if (currentRoute != item.screen.route) {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}