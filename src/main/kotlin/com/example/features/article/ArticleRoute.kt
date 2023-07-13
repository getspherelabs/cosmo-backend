package com.example.features.article

import com.example.common.exception.BadRequestException
import com.example.common.http.httpResponse
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.articleRoute() {
    val controller: ArticleController by inject()

    get(path = "/articles") {
        val articles = controller.getArticles()
        val response = httpResponse(articles)

        call.respond(response.code, response.body)
    }

    route(path = "/article") {
        post(path = "new") {
            val request = call.receive<ArticleRequest>()
            val newArticle = controller.addArticle(request)
            val response = httpResponse(newArticle)

            call.respond(response.code, response.body)
        }


        get(path = "{id}") {
            val requestId = call.parameters["id"] ?: return@get
            val newArticle = controller.getArticleById(requestId)
            val response = httpResponse(newArticle)
            call.respond(response.code, response.body)
        }

        put(path = "{id}") {
            val requestId = call.parameters["id"] ?: return@put
            val newRequest = runCatching {
                call.receive<ArticleRequest>()
            }.getOrElse {
                throw BadRequestException("Require name and description")
            }

            val newArticle = controller.updateArticle(requestId, newRequest)

            val response = httpResponse(newArticle)

            call.respond(response.code, response.body)
        }

        delete("{id}") {
            val requestId = call.parameters["id"] ?: return@delete

            val newRequest = controller.deleteArticleById(requestId)
            val response = httpResponse(newRequest)
            call.respond(response.code, response.body)
        }
    }

    delete(path = "/articles") {
        val articles = controller.removeAll()
        val response = httpResponse(articles)

        call.respond(response.code, response.body)
    }
}