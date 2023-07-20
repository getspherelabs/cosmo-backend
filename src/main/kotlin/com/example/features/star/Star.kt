package com.example.features.star

import com.example.data.entity.StarEntity

data class Star(
    val id: String,
    val name: String,
    val description: String,
    val size: String,
    val isPopular: Boolean,
    val distanceFromSun: String,
    val createdTimestamp: Long,
    val updatedTimestamp: Long,
    val image: String
)


fun StarEntity.asStar(): Star {
    return Star(
        id = this.uuid,
        name = this.name,
        description = this.description,
        size = this.size,
        isPopular = this.isPopular,
        distanceFromSun = this.distanceFromSun,
        createdTimestamp = this.createdTimestamp.millis,
        updatedTimestamp = this.updatedTimestamp.millis,
        image = this.image
    )
}

fun Star.asDto(): StarDto {
    return StarDto(
        id = this.id,
        name = this.name,
        description = this.description,
        size = this.size,
        isPopular = this.isPopular,
        distanceFromSun = this.distanceFromSun,
        createdTimestamp = this.createdTimestamp,
        updatedTimestamp = this.updatedTimestamp,
        image = this.image
    )
}