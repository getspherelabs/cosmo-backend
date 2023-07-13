package com.example.features.star

import com.example.common.exception.BadRequestException
import com.example.common.http.httpResponse
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.starRoute() {
    val controller: StarController by inject()

    get(path = "/stars") {
        val planets = controller.getStars()
        val response = httpResponse(planets)
        call.respond(response.code, response.body)
    }

    route("/star/") {
        post(path = "new") {
            val request = call.receive<StarRequest>()

            val newPlanet = controller.addStar(request)

            val response = httpResponse(newPlanet)

            call.respond(response.code, response.body)
        }

        get(path = "{id}") {
            val requestId = call.parameters["id"] ?: return@get
            val newStar = controller.getStarById(requestId)
            val response = httpResponse(newStar)
            call.respond(response.code, response.body)
        }

        put(path = "{id}") {
            val requestId = call.parameters["id"] ?: return@put
            val newRequest = runCatching {
                call.receive<StarRequest>()
            }.getOrElse {
                throw BadRequestException("Require name and description")
            }

            val newStar = controller.updateStar(requestId, newRequest)

            val response = httpResponse(newStar)

            call.respond(response.code, response.body)
        }

        delete("{id}") {
            val requestId = call.parameters["id"] ?: return@delete

            val starRequest = controller.deleteStarById(requestId)
            val response = httpResponse(starRequest)
            call.respond(response.code, response.body)
        }
    }

    get(path = "/stars/popular") {
        val popularStars = controller.getPopularStars()
        val response = httpResponse(popularStars)

        call.respond(response.code, response.body)
    }

    delete(path = "/stars") {
        val planets = controller.removeAll()
        val response = httpResponse(planets)

        call.respond(response.code, response.body)
    }
}