package com.example.entity

import com.example.table.ArticleTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.DateTime
import java.util.*

class ArticleEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    companion object : UUIDEntityClass<ArticleEntity>(ArticleTable)

    var uuid: String = id.value.toString()
    var title: String by ArticleTable.title
    var description: String by ArticleTable.description
    var author: String by ArticleTable.author
    var createdTimestamp: DateTime by ArticleTable.createdTimestamp
}
