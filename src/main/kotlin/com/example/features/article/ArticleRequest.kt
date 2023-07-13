package com.example.features.article

import kotlinx.serialization.Serializable

@Serializable
data class ArticleRequest(
    val title: String,
    val description: String,
    val author: String
)
