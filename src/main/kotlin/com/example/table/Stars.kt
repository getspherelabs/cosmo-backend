package com.example.table

import org.jetbrains.exposed.dao.id.UUIDTable

object Stars : UUIDTable() {
    var name = varchar(name = "name", length = 30)
    var size = text(name = "size")
    var distanceFromSun = text(name = "distance_from_sun")
    var description = text(name = "description")
}