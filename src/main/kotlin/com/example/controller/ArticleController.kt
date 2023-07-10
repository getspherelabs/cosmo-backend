package com.example.controller

import com.example.dao.ArticleDao
import com.example.domain.usecase.article.GetArticles
import com.example.domain.usecase.article.InsertArticle
import com.example.domain.usecase.article.RemoveArticleById
import com.example.model.ArticleDomain

interface ArticleController {
    suspend fun insert(title: String, description: String, author: String): String
    suspend fun getPlanets(): List<ArticleDomain>
    suspend fun removeById(id: String): Boolean
}

class DefaultArticleController(
    private val insertArticle: InsertArticle,
    private val getArticles: GetArticles,
    private val removeArticleById: RemoveArticleById,
    private val dao: ArticleDao
) : ArticleController {
    override suspend fun insert(title: String, description: String, author: String): String {
        return insertArticle.execute(title, description, author)
    }

    override suspend fun getPlanets(): List<ArticleDomain> {
        return dao.getArticles()
    }

    override suspend fun removeById(id: String): Boolean {
        return dao.removeById(id)
    }
}