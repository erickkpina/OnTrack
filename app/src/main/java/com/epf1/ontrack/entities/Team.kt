package com.epf1.ontrack.entities

data class Team(
    val teamId: String,
    val teamName: String,
    val country: String,
    val firstAppareance: Int, //Ta escrito desta forma nas docs deles portanto n pode ser alterado aq
    val constructorsChampionships: Int,
    val driversChampionships: Int,
    val url: String
)
