package com.epf1.ontrack.entities

data class Team(
    val teamId: String,
    val teamName: String,
    val country: String,
    val firstAppareance: Int,
    val constructorsChampionships: Int,
    val driversChampionships: Int,
    val url: String
)
