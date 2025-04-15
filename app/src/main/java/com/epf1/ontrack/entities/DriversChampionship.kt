package com.epf1.ontrack.entities

data class DriversChampionship(
    val classificationId: Int,
    val driverId: String,
    val teamId: String,
    val points: Int,
    val position: Int,
    val wins: Int,
    val driver: Driver,
    val team: Team
)