package com.example.features.star

import com.example.features.article.ArticleRequest
import com.example.features.article.ArticleIdResponse
import com.example.features.article.ArticleResponse
import com.example.features.article.ArticlesResponse

interface StarController {
    suspend fun getArticles(): ArticlesResponse
    suspend fun addArticle(request: ArticleRequest): ArticleIdResponse

    suspend fun removeAll(): ArticlesResponse

    suspend fun deleteArticleById(articleId: String): ArticleIdResponse

    suspend fun updateArticle(
        planetId: String,
        request: ArticleRequest
    ): ArticleIdResponse

    suspend fun getArticleById(
        planetId: String
    ): ArticleResponse
}