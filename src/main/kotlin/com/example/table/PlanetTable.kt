package com.example.table

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime

object PlanetTable : UUIDTable(name = "Planet") {
    var name = varchar(name = "name", length = 30)
    var size = text(name = "size")
    var distanceFromSun = text(name = "distance_from_sun")
    var description = text(name = "description")
    var createdTimestamp = datetime("created_timestamp").default(DateTime.now())
}