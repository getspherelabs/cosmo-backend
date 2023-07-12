package com.example.repository

import com.example.data.dao.ArticleDao
import com.example.data.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ArticleRepository {
    suspend fun insert(
        title: String,
        desc: String,
        author: String
    ): String

    suspend fun getArticleCounts(): Long

    suspend fun removeAll()

    suspend fun getArticles(): List<Article>

    suspend fun getArticleById(id: String): Article

    suspend fun exists(id: String): Boolean

    suspend fun deleteById(id: String): Boolean

    suspend fun update(
        id: String,
        title: String,
        desc: String,
        author: String
    ): String
}

class DefaultArticleRepository(
    private val dao: ArticleDao
) : ArticleRepository {
    override suspend fun insert(title: String, desc: String, author: String): String {
        return dao.insert(title, desc, author)
    }

    override suspend fun getArticleCounts(): Long {
        return dao.getArticleCounts()
    }

    override suspend fun removeAll() {
        dao.removeAll()
    }

    override suspend fun getArticles(): List<Article> {
        return dao.getArticles()
    }

    override suspend fun getArticleById(id: String): Article {
        return withContext(Dispatchers.IO) {
            dao.getArticleById(id)
        }
    }

    override suspend fun exists(id: String): Boolean {
        return dao.exists(id)
    }

    override suspend fun deleteById(id: String): Boolean {
        return dao.deleteById(id)
    }

    override suspend fun update(id: String, title: String, desc: String, author: String): String {
        return dao.update(id, title, desc, author)
    }
}