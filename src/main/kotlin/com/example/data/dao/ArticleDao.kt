package com.example.data.dao

import com.example.common.dbQuery
import com.example.data.entity.ArticleEntity
import com.example.data.model.Article
import com.example.data.model.asArticle
import java.util.UUID


interface ArticleDao {
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

class DefaultArticleDao : ArticleDao {
    override suspend fun insert(title: String, desc: String, author: String): String = dbQuery {
        ArticleEntity.new {
            this.title = title
            this.description = desc
            this.author = author
        }.id.value.toString()
    }

    override suspend fun getArticleCounts(): Long = dbQuery {
        ArticleEntity.all().count()
    }

    override suspend fun removeAll() = dbQuery {
        ArticleEntity.all().forEach { it.delete() }
    }

    override suspend fun getArticles(): List<Article> = dbQuery {
        ArticleEntity.all().sortedBy { it.id }.map { it.asArticle() }
    }

    override suspend fun getArticleById(id: String): Article = dbQuery {
        checkNotNull(ArticleEntity.findById(UUID.fromString(id))).asArticle()
    }

    override suspend fun exists(id: String): Boolean = dbQuery {
        ArticleEntity.findById(UUID.fromString(id)) != null
    }

    override suspend fun deleteById(id: String): Boolean = dbQuery {
        val article = ArticleEntity.findById(UUID.fromString(id))

        article?.run {
            delete()
            return@dbQuery true
        }
        return@dbQuery false
    }

    override suspend fun update(id: String, title: String, desc: String, author: String): String = dbQuery {
        ArticleEntity[UUID.fromString(id)].apply {
            this.title = title
            this.description = desc
            this.author = author
        }.id.value.toString()
    }
}