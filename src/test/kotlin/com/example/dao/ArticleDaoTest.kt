package com.example.dao

import com.example.common.dbQuery
import com.example.core.CosmoTest
import com.example.data.dao.ArticleDao
import com.example.data.dao.DefaultArticleDao
import com.example.di.testModule
import com.example.testKoinModule
import io.github.serpro69.kfaker.faker
import io.ktor.server.testing.*

import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

import kotlin.test.assertEquals

class ArticleDaoTest : CosmoTest() {

    private val articleDao: ArticleDao by inject()
    private val faker = faker { }

    @Test
    fun `should insert article and verify counts`() = withApp {
        val articleTitle = faker.space.moon()
        val desc = faker.space.star()
        val author = faker.name.name()

        articleDao.insert(articleTitle, desc, author)
        articleDao.insert(articleTitle, desc, author)

        val articles = articleDao.getArticleCounts()

        assertEquals(2, articles)

        articleDao.removeAll()
    }

    @Test
    fun `should insert article and get articles`() = withApp {
        val articleTitle = faker.space.moon()
        val desc = faker.space.star()
        val author = faker.name.name()

        articleDao.insert(articleTitle, desc, author)
        articleDao.insert(articleTitle, desc, author)

        val articles = articleDao.getArticles()

        println(articles)

        assertEquals(desc, articles.first().description)
    }

    @Test
    fun `should insert articles and remove all`() = withApp {
        val articleTitle = faker.space.moon()
        val desc = faker.space.star()
        val author = faker.name.name()

        articleDao.insert(articleTitle, desc, author)
        articleDao.insert(articleTitle, desc, author)

        articleDao.removeAll()

        val articles = articleDao.getArticles()

        assertEquals(0, articles.size)
    }

    @Test
    fun `should insert and get by id`() = withApp {
        val articleTitle = faker.space.moon()
        val desc = faker.space.star()
        val author = faker.name.name()

        articleDao.insert(articleTitle, desc, author)
        articleDao.insert(articleTitle, desc, author)

        val firstArticle = articleDao.getArticles().first()
        val newArticle = articleDao.getArticleById(firstArticle.id)

        assertEquals(newArticle, firstArticle)

    }

    @Test
    fun `should insert and check id`() = withApp {
        val articleTitle = faker.space.moon()
        val desc = faker.space.star()
        val author = faker.name.name()

        articleDao.insert(articleTitle, desc, author)
        articleDao.insert(articleTitle, desc, author)

        val firstArticle = articleDao.getArticles().first()
        val result = articleDao.exists(firstArticle.id)

        assertEquals(true, result)
    }

    @Test
    fun `should insert articles and delete by id`() = withApp {
        val articleTitle = faker.space.moon()
        val desc = faker.space.star()
        val author = faker.name.name()

        articleDao.insert(articleTitle, desc, author)
        articleDao.insert(articleTitle, desc, author)

        val firstArticle = articleDao.getArticles().first()
        val result = articleDao.deleteById(firstArticle.id)
        val articles = articleDao.getArticles()

        assertEquals(true, result)
        assertEquals(1, articles.size)
    }
}