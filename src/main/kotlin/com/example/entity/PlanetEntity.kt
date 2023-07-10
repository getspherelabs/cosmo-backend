package com.example.entity

import com.example.model.PlanetDomain
import com.example.table.PlanetTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class PlanetEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<PlanetEntity>(PlanetTable)

    var name by PlanetTable.name
    var size by PlanetTable.size
    var description by PlanetTable.description
    var distanceFromSun by PlanetTable.distanceFromSun
    var createdTimestamp by PlanetTable.createdTimestamp
}

fun PlanetEntity.asPlanet(): PlanetDomain {
    return PlanetDomain(
        this.id.value.toString(),
        this.name,
        this.description,
        this.createdTimestamp.millis,
        this.distanceFromSun
    )
}
