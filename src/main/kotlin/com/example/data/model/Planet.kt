package com.example.data.model

import com.example.data.entity.PlanetEntity
import com.example.http.response.PlanetDto


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

fun Planet.asDto(): PlanetDto {
    return PlanetDto(
        this.id,
        this.name,
        this.description,
        this.size,
        this.distanceFromSun,
        this.isPopular,
        this.createdTimestamp,
        this.updatedTimestamp
    )
}