package com.epf1.ontrack.entities

data class ConstructorsChampionship(
    val classificationId: Int,
    val teamId: String,
    val points: Double,
    val position: Int,
    val wins: Int,
    val team: Team
)
