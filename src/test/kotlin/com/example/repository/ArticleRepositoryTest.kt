package com.example.repository

import com.example.core.CosmoTest
import com.example.features.article.ArticleRepository
import com.example.utils.Faker
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class ArticleRepositoryTest : CosmoTest() {

    private val articleRepository: ArticleRepository by inject()

    @Test
    fun `should insert article and verify counts`() = withApp {
        val articleTitle = Faker.Article.title
        val desc = Faker.Article.description
        val author = Faker.Article.author

        articleRepository.insert(articleTitle, desc, author)
        articleRepository.insert(articleTitle, desc, author)

        val articles = articleRepository.getArticleCounts()

        assertEquals(2, articles)

        articleRepository.removeAll()
    }

    @Test
    fun `should insert article and get articles`() = withApp {
        val articleTitle = Faker.Article.title
        val desc = Faker.Article.description
        val author = Faker.Article.author

        articleRepository.insert(articleTitle, desc, author)
        articleRepository.insert(articleTitle, desc, author)

        val articles = articleRepository.getArticles()

        println(articles)

        assertEquals(desc, articles.first().description)
    }

    @Test
    fun `should insert articles and remove all`() = withApp {
        val articleTitle = Faker.Article.title
        val desc = Faker.Article.description
        val author = Faker.Article.author

        articleRepository.insert(articleTitle, desc, author)
        articleRepository.insert(articleTitle, desc, author)

        articleRepository.removeAll()

        val articles = articleRepository.getArticles()

        assertEquals(0, articles.size)
    }

    @Test
    fun `should insert and get by id`() = withApp {
        val articleTitle = Faker.Article.title
        val desc = Faker.Article.description
        val author = Faker.Article.author

        articleRepository.insert(articleTitle, desc, author)
        articleRepository.insert(articleTitle, desc, author)

        val firstArticle = articleRepository.getArticles().first()
        val newArticle = articleRepository.getArticleById(firstArticle.id)

        assertEquals(newArticle, firstArticle)

    }

    @Test
    fun `should insert and check id`() = withApp {
        val articleTitle = Faker.Article.title
        val desc = Faker.Article.description
        val author = Faker.Article.author

        articleRepository.insert(articleTitle, desc, author)
        articleRepository.insert(articleTitle, desc, author)

        val firstArticle = articleRepository.getArticles().first()
        val result = articleRepository.exists(firstArticle.id)

        assertEquals(true, result)
    }

    @Test
    fun `should insert articles and delete by id`() = withApp {
        val articleTitle = Faker.Article.title
        val desc = Faker.Article.description
        val author = Faker.Article.author

        articleRepository.insert(articleTitle, desc, author)
        articleRepository.insert(articleTitle, desc, author)

        val firstArticle = articleRepository.getArticles().first()
        val result = articleRepository.deleteById(firstArticle.id)
        val articles = articleRepository.getArticles()

        assertEquals(true, result)
        assertEquals(1, articles.size)
    }
}