package com.example.route

import com.example.common.http.Status
import com.example.core.CosmoTest
import com.example.features.article.ArticleIdResponse
import com.example.features.article.ArticleRequest
import com.example.features.article.ArticleResponse
import com.example.features.article.ArticlesResponse
import com.example.utils.*
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ArticleRouteTest: CosmoTest() {

    @Test
    fun `when the article insert and check the response is success`() = withApp {
        val articleName = Faker.Article.title
        val desc = Faker.Article.description
        val author = Faker.Article.author

        val request = ArticleRequest(
            title = articleName,
            description = desc,
            author = author
        ).toJson()

        post("$v1/article/new", request).content.toDto<ArticleIdResponse>().let { newResponse ->
            assertEquals(newResponse.status, Status.Success)
            assertNotNull(newResponse.id)
        }
    }

    @Test
    fun `when the article insert and get articles`() = withApp {
        val articleName = Faker.Article.title
        val desc = Faker.Article.description
        val author = Faker.Article.author

        val request = ArticleRequest(
            title = articleName,
            description = desc,
            author = author
        ).toJson()

        post("$v1/article/new", request)

        get("$v1/articles").content.toDto<ArticlesResponse>().let { newResponse ->
            assertEquals(newResponse.status, Status.Success)
            assertEquals(newResponse.articles.first().title, articleName)
        }
    }

    @Test
    fun `when user insert article and check getting planet by id`() = withApp {
        val articleName = Faker.Article.title
        val desc = Faker.Article.description
        val author = Faker.Article.author

        val request = ArticleRequest(
            title = articleName,
            description = desc,
            author = author
        ).toJson()

        val newRequest = post("$v1/article/new", request).content.toDto<ArticleIdResponse>()

        get("$v1/article/${newRequest.id}").content.toDto<ArticleResponse>().let { newResponse ->
            assertEquals(newResponse.status, Status.Success)
            assertEquals(newResponse.article?.title, articleName)
        }
    }

    @Test
    fun `when user insert article and check getting article by id when it is not found`() = withApp {
        val id = UUID.randomUUID().toString()

        get("$v1/planet/$id").content.toDto<ArticleResponse>().let { newResponse ->
            assertEquals(newResponse.status, Status.NotFound)
            assertNull(newResponse.article)
        }
    }

    companion object {
        private const val v1 = "/api/v1"
    }
}