package com.example.repository

import com.example.dao.ArticleDao
import com.example.model.ArticleDomain

interface ArticleRepository {
    suspend fun insert(title: String, description: String, author: String): String
    suspend fun getArticles(): List<ArticleDomain>
    suspend fun removeById(id: String): Boolean
}

class DefaultArticleRepository(
    private val dao: ArticleDao
) : ArticleRepository {
    override suspend fun insert(title: String, description: String, author: String): String {
        return dao.insert(title, description, author)
    }

    override suspend fun getArticles(): List<ArticleDomain> {
        return dao.getArticles()
    }

    override suspend fun removeById(id: String): Boolean {
        return dao.removeById(id)
    }
}