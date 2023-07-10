package com.example.dao

import com.example.common.dbQuery
import com.example.entity.ArticleEntity
import com.example.model.ArticleDomain
import com.example.model.asArticle
import java.util.*

interface ArticleDao {
    suspend fun insert(title: String, description: String, author: String): String
    suspend fun getArticles(): List<ArticleDomain>
    suspend fun removeById(id: String): Boolean
}

class DefaultArticleDao : ArticleDao {
    override suspend fun insert(title: String, description: String, author: String): String {
        return ArticleEntity.new {
            this.title = title
            this.author = author
            this.description = description
        }.id.value.toString()
    }

    override suspend fun getArticles(): List<ArticleDomain> {
        return ArticleEntity.all().sortedBy { it.id }.map { it.asArticle() }
    }

    override suspend fun removeById(id: String): Boolean = dbQuery {
        val currentPlant = ArticleEntity.findById(UUID.fromString(id))
        currentPlant?.run {
            delete()
            return@dbQuery true
        }
        return@dbQuery false
    }
}