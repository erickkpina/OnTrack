package com.epf1.ontrack.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.epf1.ontrack.viewmodel.DriversStandingsViewModel

@Composable
fun DriversStandingsScreen(viewModel: DriversStandingsViewModel = viewModel()) {
    val standings = viewModel.standings.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Drivers Standings") })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(standings) { champ ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "${champ.position}. ${champ.driver.name} ${champ.driver.surname}",
                        style = MaterialTheme.typography.h6
                    )
                    Text(text = "Team: ${champ.team.teamName}")
                    Text(text = "Points: ${champ.points} | Wins: ${champ.wins}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider()
                }
            }
        }
    }
}
