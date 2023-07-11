package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class PlanetDomain(
    val id: String,
    val name: String,
    val description: String,
    val createdTimestamp: Long,
    val distanceFromSun: String
)

