package com.epf1.ontrack.responses

import com.epf1.ontrack.entities.ConstructorsChampionship

data class ConstructorsStandingsResponse(
    val season: String,
    val championshipId: String,
    val constructors_championship: List<ConstructorsChampionship>
)
