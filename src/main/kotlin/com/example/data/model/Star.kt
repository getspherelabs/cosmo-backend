package com.example.data.model

import com.example.data.entity.StarEntity

data class Star(
    val id: String,
    val name: String,
    val description: String,
    val size: String,
    val distanceFromSun: String,
    val createdTimestamp: Long,
    val updatedTimestamp: Long
)


fun StarEntity.asStar(): Star {
    return Star(
        id = this.uuid,
        name = this.name,
        description = this.description,
        size = this.size,
        distanceFromSun = this.distanceFromSun,
        createdTimestamp = this.createdTimestamp.millis,
        updatedTimestamp = this.updatedTimestamp.millis
    )
}