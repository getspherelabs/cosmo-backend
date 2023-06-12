package com.example.entity

import com.example.model.Star
import com.example.table.StarTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class StarEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    var name by StarTable.name
    var description by StarTable.description
    var size by StarTable.size
    var distanceFromSun by StarTable.distanceFromSun
    var createdTimestamp by StarTable.createdTimestamp

    companion object : UUIDEntityClass<StarEntity>(StarTable)
}


fun StarEntity.asStar(): Star {
    return Star(
        id = this.id.value.toString(),
        name = this.name,
        description = this.description,
        size = this.size,
        distanceFromSun = this.distanceFromSun,
        createdTimestamp = this.createdTimestamp.millis
    )
}