package com.example.data.entity

import com.example.data.table.PlanetTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.DateTime
import java.util.UUID

class PlanetEntity(id: EntityID<UUID>): UUIDEntity(id) {
    companion object: UUIDEntityClass<PlanetEntity>(PlanetTable)

    var uuid: String = id.value.toString()
    var name: String by PlanetTable.name
    var description: String by PlanetTable.description
    var size: String by PlanetTable.size
    var distanceFromSun: String by PlanetTable.distanceFromSun
    var isPopular: Boolean by PlanetTable.isPopular
    var createdTimestamp: DateTime by PlanetTable.createdTimestamp
    var updatedTimestamp: DateTime by PlanetTable.updatedTimestamp
}