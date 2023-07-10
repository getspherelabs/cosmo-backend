package com.example.domain.usecase.article

import com.example.model.ArticleDomain
import com.example.repository.ArticleRepository

interface GetArticles {
    suspend fun execute(): List<ArticleDomain>
}

class DefaultGetArticles(
    private val repository: ArticleRepository
): GetArticles {
    override suspend fun execute(): List<ArticleDomain> {
        return repository.getArticles()
    }
}