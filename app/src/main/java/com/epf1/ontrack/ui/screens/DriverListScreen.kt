package com.epf1.ontrack.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.epf1.ontrack.viewmodel.MainViewModel

@Composable
fun DriverListScreen(viewModel: MainViewModel) {
    val drivers = viewModel.drivers.collectAsState().value

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

@Composable
@Preview
fun previewScreen() {
    val viewModel: MainViewModel = viewModel()
    DriverListScreen(viewModel)
}
