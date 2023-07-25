package com.example.features.article

import com.example.common.http.Response
import com.example.common.http.Status
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    val id: String,
    val title: String,
    val description: String,
    val author: String,
    val createdTimestamp: Long,
    val updatedTimestamp: Long
)

@Serializable
data class ArticlesResponse(
    override val status: Status,
    override val message: String,
    val articles: List<ArticleDto> = emptyList()
) : Response {
    companion object {
        fun onSuccess(articles: List<ArticleDto>) = ArticlesResponse(
            Status.Success,
            "Get planets successful",
            articles
        )

        fun onFailure(message: String) = ArticlesResponse(
            Status.Failed,
            message
        )
    }
}

@Serializable
data class ArticleIdResponse(
    override val status: Status,
    override val message: String,
    val id: String? = null
) : Response {

    companion object {
        fun onFailure(message: String): ArticleIdResponse {
            return ArticleIdResponse(
                Status.Failed,
                message
            )
        }

        fun onNotFound(message: String): ArticleIdResponse {
            return ArticleIdResponse(
                Status.NotFound,
                message
            )
        }

        fun onSuccess(id: String) = ArticleIdResponse(
            Status.Success,
            "Task was successful",
            id
        )
    }
}

@Serializable
data class ArticleResponse(
    override val status: Status,
    override val message: String,
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val author: String = "",
    val createdTimestamp: Long = 0L,
    val updatedTimestamp: Long = 0L
) : Response {
    companion object {
        fun onFailure(message: String): ArticleResponse {
            return ArticleResponse(
                Status.Failed,
                message
            )
        }

        fun onNotFound(message: String): ArticleResponse {
            return ArticleResponse(
                Status.NotFound,
                message
            )
        }

        fun onSuccess(article: ArticleDto) = ArticleResponse(
            Status.Success,
            "Task was successful",
            id = article.id,
            author = article.author,
            description = article.description,
            title = article.title,
            createdTimestamp = article.createdTimestamp,
            updatedTimestamp = article.updatedTimestamp
        )
    }
}