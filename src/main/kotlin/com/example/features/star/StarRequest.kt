package com.example.features.star

import kotlinx.serialization.Serializable

@Serializable
data class StarRequest(
    val name: String,
    val description: String,
    val size: String,
    val distanceFromSun: String,
    val isPopular: Boolean = false,
)
