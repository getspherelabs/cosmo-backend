package com.example.model

import com.example.entity.ArticleEntity

data class ArticleDomain(
    val id: String,
    val title: String,
    val description: String,
    val author: String,
    val createdTimestamp: Long
)

fun ArticleEntity.asArticle(): ArticleDomain {
    return ArticleDomain(
        id = id.value.toString(),
        title = title,
        description = description,
        author = author,
        createdTimestamp = createdTimestamp.millis
    )
}
