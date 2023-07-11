package com.example.data.table

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime

object ArticleTable : UUIDTable() {
    var title = varchar(name = "article_title", length = 30)
    var description = text(name = "article_description")
    var createdTimestamp = datetime("created_timestamp").default(DateTime.now())
    var updatedTimestamp = datetime("updated_timestamp").default(DateTime.now())
    var author = varchar(name = "article_author", length = 20)
}