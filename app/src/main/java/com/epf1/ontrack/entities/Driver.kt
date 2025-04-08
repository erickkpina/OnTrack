package com.epf1.ontrack.entities

data class Driver(
    val driverId: String,
    val name: String,
    val surname: String,
    val nationality: String,
    val birthday: String,
    val number: Int,
    val shortName: String,
    val url: String
)