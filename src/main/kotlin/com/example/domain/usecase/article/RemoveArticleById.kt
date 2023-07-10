package com.example.domain.usecase.article

import com.example.repository.ArticleRepository

interface RemoveArticleById {
    suspend fun execute(id: String): Boolean
}

class DefaultRemoveArticleById(
    private val repository: ArticleRepository
) : RemoveArticleById {
    override suspend fun execute(id: String): Boolean {
        return repository.removeById(id)
    }
}