package com.epf1.ontrack.responses

import com.epf1.ontrack.entities.DriversChampionship

data class DriversStandingsResponse(
    val season: Int,
    val championshipId: String,
    val drivers_championship: List<DriversChampionship>
)
