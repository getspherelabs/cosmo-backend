package com.example.table

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime

object ArticleTable: UUIDTable() {
    var title = varchar(name = "title", length = 30)
    var description = text(name = "description")
    var author = varchar(name = "author", length = 15)
    var createdTimestamp = datetime("created_timestamp").default(DateTime.now())
}
