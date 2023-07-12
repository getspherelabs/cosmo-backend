package com.example.http.request

import kotlinx.serialization.Serializable

@Serializable
data class ArticleRequest(
    val title: String,
    val description: String,
    val author: String
)
