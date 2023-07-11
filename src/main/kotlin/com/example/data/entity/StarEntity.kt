package com.example.data.entity

import com.example.data.table.PlanetTable
import com.example.table.StarTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.DateTime
import java.util.UUID

class StarEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    companion object : UUIDEntityClass<StarEntity>(StarTable)

    var uuid: String = id.value.toString()
    var name: String by StarTable.name
    var description: String by StarTable.description
    var size: String by StarTable.size
    var distanceFromSun: String by StarTable.distanceFromSun
    var createdTimestamp: DateTime by PlanetTable.createdTimestamp
    var updatedTimestamp: DateTime by PlanetTable.updatedTimestamp
}