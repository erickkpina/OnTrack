package com.epf1.ontrack.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
    selectedYear: Int,
    onYearSelected: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    val currentYear = LocalDateTime.now().year
    val years = (1950..currentYear).toList().reversed()
    val initialIndex = years.indexOf(selectedYear)

    var tempSelectedYear by remember { mutableStateOf(selectedYear) }
    val listState = rememberLazyListState()

    // Scroll to the selected year when dialog opens
    LaunchedEffect(Unit) {
        if (initialIndex != -1) {
            listState.scrollToItem(initialIndex)
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        shape = MaterialTheme.shapes.medium,
        containerColor = Color(0xFF2C2C2C),
        titleContentColor = Color.White,
        textContentColor = Color.White,
        title = {
            Text(
                "Select a Year",
                fontSize = 20.sp,
                color = Color.White
            )
        },
        text = {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(years) { year ->
                    TextButton(onClick = {
                        tempSelectedYear = year
                    }) {
                        Text(
                            text = year.toString(),
                            fontSize = 18.sp,
                            color = if (year == tempSelectedYear) Color.Red else Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onYearSelected(tempSelectedYear)
                onDismiss()
            }) {
                Text("Confirm", color = Color.Red, fontSize = 18.sp)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = Color.LightGray)
            }
        }
    )
}
