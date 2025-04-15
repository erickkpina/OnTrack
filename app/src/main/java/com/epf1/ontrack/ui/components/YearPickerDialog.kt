package com.epf1.ontrack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDateTime

@Composable
fun YearPickerDialog(
    currentYear: Int = LocalDateTime.now().year,
    startYear: Int = 1950,
    onYearSelected: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    val years = (startYear..currentYear).toList().reversed()
    var selectedYear by remember { mutableStateOf(currentYear) }

    AlertDialog(
        modifier = Modifier
            .height(400.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant),
        onDismissRequest = onDismiss,
        title = {
            Text(
                "Select a Year",
                fontSize = 20.sp,
                color = Color.White
            )
        },
        text = {
            LazyColumn(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(years) { year ->
                    TextButton(onClick = {
                        selectedYear = year
                    }) {
                        Text(
                            text = year.toString(),
                            fontSize = 18.sp,
                            color = if (year == selectedYear) Color.Red else Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onYearSelected(selectedYear)
                onDismiss()
            }) {
                Text(
                    "Confirm",
                    color = Color.Red,
                    fontSize = 18.sp
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = Color.LightGray)
            }
        },
        containerColor = Color(0xFF2C2C2C), // Dark gray background
        titleContentColor = Color.White,
        textContentColor = Color.White
    )
}
