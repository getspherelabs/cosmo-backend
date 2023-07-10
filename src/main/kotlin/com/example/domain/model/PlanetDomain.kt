package com.example.domain.model

data class PlanetDomain(
    val id: String,
    val name: String,
    val description: String,
    val createdTimestamp: Long,
    val distanceFromSun: String
)

