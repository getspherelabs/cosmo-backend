package com.example.features.article

import com.example.data.entity.ArticleEntity


data class Article(
    val id: String,
    val title: String,
    val description: String,
    val author: String,
    val createdTimestamp: Long,
    val updatedTimestamp: Long
)

fun ArticleEntity.asArticle(): Article {
    return Article(
        id = this.uuid,
        title = this.title,
        description = this.description,
        author = this.author,
        createdTimestamp = this.createdTimestamp.millis,
        updatedTimestamp = this.updatedTimestamp.millis

    )
}

fun Article.asDto(): ArticleDto {
    return ArticleDto(
        id = this.id,
        title = this.title,
        description = this.description,
        author = this.author,
        createdTimestamp = this.createdTimestamp,
        updatedTimestamp = this.updatedTimestamp
    )
}