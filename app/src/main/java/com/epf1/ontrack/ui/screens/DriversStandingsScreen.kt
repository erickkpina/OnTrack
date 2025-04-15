package com.epf1.ontrack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.epf1.ontrack.viewmodel.DriversStandingsViewModel
import java.time.LocalDateTime
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.epf1.ontrack.ui.components.LoadingDialog
import com.epf1.ontrack.ui.components.YearPickerDialog

@Composable
fun DriversStandingsScreen(viewModel: DriversStandingsViewModel = viewModel()) {
    val standings = viewModel.standings.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    var showYearPickerDialog by remember { mutableStateOf(false) }
    var selectedYear by remember { mutableIntStateOf(LocalDateTime.now().year) }

    if (isLoading) {
        LoadingDialog()
    }

    if (showYearPickerDialog) {
        YearPickerDialog(
            onYearSelected = { year ->
                selectedYear = year
                // Adicionar a chamada a função no ViewModel para buscar os standings do ano
            },
            onDismiss = { showYearPickerDialog = false }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Drivers Standings - $selectedYear") },
                actions = {
                    IconButton(onClick = { showYearPickerDialog = true }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Select Year")
                    }
                }
            )
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
