package com.example.data.table

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime

object PlanetTable : UUIDTable() {
    var name = varchar(name = "planet_name", length = 20)
    var description = text(name = "planet_description")
    var size = varchar(name = "planet_size", length = 20)
    var distanceFromSun = text(name = "planet_distance_from_sun")
    var isPopular = bool(name = "is_popular")
    var createdTimestamp = datetime(name = "created_timestamp").default(DateTime.now())
    var updatedTimestamp = datetime(name = "updated_timestamp").default(DateTime.now())
    var image = text(name = "planet_image").default("")
}