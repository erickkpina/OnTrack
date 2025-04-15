package com.epf1.ontrack.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.epf1.ontrack.viewmodel.StandingsViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.epf1.ontrack.MainActivity
import com.epf1.ontrack.ui.components.LoadingDialog
import com.epf1.ontrack.ui.components.YearPickerDialog
import com.epf1.ontrack.R
import kotlinx.coroutines.launch

@Composable
fun StandingsScreen(viewModel: StandingsViewModel = viewModel()) {
    val pagerState = rememberPagerState(pageCount = { StandingsTabs.entries.size })
    val selectedTabIndex = remember {  derivedStateOf { pagerState.currentPage } }

    val driversStandings = viewModel.driversStandings.collectAsState().value
    val constructorsStandings = viewModel.constructorsStandings.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    var showYearPickerDialog by remember { mutableStateOf(false) }
    var selectedYear by remember { mutableIntStateOf(viewModel.getCurrentYear()) }

    if (isLoading) {
        LoadingDialog()
    }

    if (showYearPickerDialog) {
        YearPickerDialog(
            selectedYear = selectedYear,
            onYearSelected = { year ->
                if (year != selectedYear) {
                    selectedYear = year
                    viewModel.fetchStandings(year)
                }
            },
            onDismiss = { showYearPickerDialog = false }
        )
    }

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MainActivity.TOP_BAR_COLOR,
                title = { Text("Drivers Standings - $selectedYear", color = MainActivity.TITLE_IN_DARK_BG) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showYearPickerDialog = true },
                backgroundColor = MainActivity.SELECTION_COLOR,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.DateRange, contentDescription = "Select Year")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding())
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex.value,
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = MainActivity.TOP_BAR_COLOR,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex.value]),
                        color = MainActivity.SELECTION_COLOR // Indicador vermelho
                    )
                }
            ) {
                StandingsTabs.entries.forEachIndexed { index, currentTab ->
                    Tab(
                        selected = selectedTabIndex.value == index,
                        text = { Text(text = currentTab.text, color = if (selectedTabIndex.value == index) MainActivity.SELECTION_COLOR else MainActivity.DESCRIPTION_IN_DARK_BG) },
                        icon = {
                            Image(
                                painter = painterResource(id = if (selectedTabIndex.value == index) currentTab.selectedIcon else currentTab.unselectedIcon),
                                contentDescription = "Tab Icon",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                when (page) {
                    StandingsTabs.Drivers.ordinal -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding)
                        ) {
                            items(driversStandings) { ds ->
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    Text(
                                        text = "${ds.position}. ${ds.driver.name} ${ds.driver.surname}",
                                        style = MaterialTheme.typography.h6
                                    )
                                    Text(text = "Team: ${ds.team.teamName}")
                                    Text(text = "Points: ${ds.points} | Wins: ${ds.wins}")
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Divider()
                                }
                            }
                        }
                    }
                    StandingsTabs.Constructors.ordinal -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding)
                        ) {
                            items(constructorsStandings) { cs ->
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    Text(
                                        text = "${cs.position}. ${cs.team.teamName}",
                                        style = MaterialTheme.typography.h6
                                    )
                                    Text(text = "Points: ${cs.points} | Wins: ${cs.wins}")
                                    Text(text = "Country: ${cs.team.country}")
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Divider()
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}

enum class StandingsTabs(
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val text: String
){
    Drivers(
        selectedIcon = R.drawable.ic_race_driver_selected,
        unselectedIcon = R.drawable.ic_racing_driver,
        text = "Drivers Standings"
    ),
    Constructors(
        selectedIcon = R.drawable.ic_race_team_selected,
        unselectedIcon = R.drawable.ic_race_team,
        text = "Constructors Standings"
    )
}


