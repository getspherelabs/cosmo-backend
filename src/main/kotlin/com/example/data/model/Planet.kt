package com.example.data.model

import com.example.data.entity.PlanetEntity


data class Planet(
    val id: String,
    val name: String,
    val description: String,
    val size: String,
    val distanceFromSun: String,
    val isPopular: Boolean,
    val createdTimestamp: Long,
    val updatedTimestamp: Long
)


fun PlanetEntity.asPlanet(): Planet {
    return Planet(
        this.uuid,
        this.name,
        this.description,
        this.size,
        this.distanceFromSun,
        this.isPopular,
        this.createdTimestamp.millis,
        this.updatedTimestamp.millis
    )
}