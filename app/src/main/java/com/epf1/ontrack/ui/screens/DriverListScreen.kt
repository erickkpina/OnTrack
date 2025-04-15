package com.epf1.ontrack.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.epf1.ontrack.ui.components.LoadingDialog
import com.epf1.ontrack.viewmodel.MainViewModel

@Composable
fun DriverListScreen(viewModel: MainViewModel) {
    val drivers = viewModel.drivers.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value

    if (isLoading) {
        LoadingDialog()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("F1 Drivers") })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            items(drivers) { driver ->
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)) {
                    Text(text = "${driver.name} ${driver.surname}", style = MaterialTheme.typography.h6)
                    Text(text = "Nationality: ${driver.nationality}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider()
                }
            }
        }
    }
}
