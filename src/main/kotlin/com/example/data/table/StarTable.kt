package com.example.data.table

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime

object StarTable : UUIDTable() {
    var name = varchar(name = "star_name", length = 20)
    var description = text(name = "star_description")
    var size = varchar(name = "star_size", length = 20)
    var distanceFromSun = text(name = "planet_distance_from_sun")
    var isPopular = bool(name = "is_popular").default(false)
    var createdTimestamp = datetime(name = "created_timestamp").default(DateTime.now())
    var updatedTimestamp = datetime(name = "updated_timestamp").default(DateTime.now())
}