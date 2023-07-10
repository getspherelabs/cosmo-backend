package com.example.domain.usecase.article

import com.example.controller.ArticleController
import com.example.repository.ArticleRepository

interface InsertArticle {
    suspend fun execute(title: String, description: String, author: String): String
}

class DefaultInsertArticle(
    private val repository: ArticleRepository
) : InsertArticle {
    override suspend fun execute(title: String, description: String, author: String): String {
        return repository.insert(title, description, author)
    }
}