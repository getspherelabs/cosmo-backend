package com.example.features.article

import com.example.common.BadRequestException
import com.example.common.NotFoundException

interface ArticleController {
    suspend fun getArticles(): ArticlesResponse
    suspend fun addArticle(request: ArticleRequest): ArticleIdResponse

    suspend fun removeAll(): ArticlesResponse

    suspend fun deleteArticleById(articleId: String): ArticleIdResponse

    suspend fun updateArticle(
        planetId: String,
        request: ArticleRequest
    ): ArticleIdResponse

    suspend fun getArticleById(
        planetId: String
    ): ArticleResponse
}

class DefaultArticleController(
    private val repository: ArticleRepository
) : ArticleController {
    override suspend fun getArticles(): ArticlesResponse {
        val articles = repository.getArticles()

        return ArticlesResponse.onSuccess(articles.map { newArticle ->
            ArticleDto(
                newArticle.id,
                newArticle.title,
                newArticle.description,
                newArticle.author,
                newArticle.createdTimestamp,
                newArticle.updatedTimestamp
            )
        })
    }

    override suspend fun addArticle(request: ArticleRequest): ArticleIdResponse {
        return try {
            val name = request.title.trim()
            val description = request.description.trim()
            val author = request.author.trim()

            validateArticleOrThrow(name, description, author)

            val articleId = repository.insert(name, description, author)

            ArticleIdResponse.onSuccess(articleId)
        } catch (e: BadRequestException) {
            ArticleIdResponse.onFailure(e.message)
        }
    }

    override suspend fun removeAll(): ArticlesResponse {
        return try {
            repository.removeAll()
            ArticlesResponse.onSuccess(emptyList())
        } catch (e: Exception) {
            val failureMsg = checkNotNull(e.message)
            ArticlesResponse.onFailure(failureMsg)
        }
    }

    override suspend fun deleteArticleById(articleId: String): ArticleIdResponse {
        return try {
            hasExist(articleId)

            if (repository.deleteById(articleId)) {
                ArticleIdResponse.onSuccess(articleId)
            } else {
                ArticleIdResponse.onFailure("Something went wrong!")
            }
        } catch (badRequestException: BadRequestException) {
            ArticleIdResponse.onFailure(badRequestException.message)
        } catch (nfe: NotFoundException) {
            ArticleIdResponse.onFailure(nfe.message)
        }
    }

    override suspend fun updateArticle(planetId: String, request: ArticleRequest): ArticleIdResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getArticleById(planetId: String): ArticleResponse {
        TODO("Not yet implemented")
    }

    private suspend fun hasExist(id: String) {
        if (!repository.exists(id)) {
            throw NotFoundException(message = "Not exist planet")
        }
    }

    private fun validateArticleOrThrow(
        title: String,
        description: String,
        author: String
    ) {
        val message = when {
            (title.isBlank() or description.isBlank()) -> "Title and description must not be blank."
            author.isBlank() -> "Article must not be blank."
            else -> return

        }
        throw BadRequestException(message)
    }
}