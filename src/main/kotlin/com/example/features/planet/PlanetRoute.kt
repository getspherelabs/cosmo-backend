package com.example.features.planet

import com.example.common.exception.BadRequestException
import com.example.common.http.httpResponse
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.planetRoute() {
    val controller: PlanetController by inject()

    get(path = "/planets") {
        val planets = controller.getPlanets()
        val response = httpResponse(planets)
        call.respond(response.code, response.body)
    }

    get(path = "/planets/{query}") {
        val query = call.parameters["query"] ?: return@get
        val newPlanets = controller.searchPlanets(query)
        val newResponse = httpResponse(newPlanets)
        call.respond(newResponse.code, newResponse.body)
    }

    route("/planet/") {
        post(path = "new") {
            val request = call.receive<PlanetRequest>()

            val newPlanet = controller.addPlanet(request)

            val response = httpResponse(newPlanet)

            call.respond(response.code, response.body)
        }

        get(path = "{id}") {
            val requestId = call.parameters["id"] ?: return@get
            val newPlanet = controller.getPlanetById(requestId)
            val response = httpResponse(newPlanet)
            call.respond(response.code, response.body)
        }

        put(path = "{id}") {
            val requestId = call.parameters["id"] ?: return@put
            val newRequest = runCatching {
                call.receive<PlanetRequest>()
            }.getOrElse {
                throw BadRequestException("Require name and description")
            }

            val newPlanet = controller.updatePlanet(requestId, newRequest)

            val response = httpResponse(newPlanet)

            call.respond(response.code, response.body)
        }

        delete("{id}") {
            val requestId = call.parameters["id"] ?: return@delete

            val planetRequest = controller.deletePlanetById(requestId)
            val response = httpResponse(planetRequest)
            call.respond(response.code, response.body)
        }
    }

    get(path = "/planets/popular") {
        val popularPlanets = controller.getPopularPlanets()
        val response = httpResponse(popularPlanets)

        call.respond(response.code, response.body)
    }

    delete(path = "/planets") {
        val planets = controller.removeAll()
        val response = httpResponse(planets)

        call.respond(response.code, response.body)
    }
}