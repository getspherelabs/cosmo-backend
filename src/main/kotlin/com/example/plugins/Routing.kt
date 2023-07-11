package com.example.plugins

import com.example.dao.PlanetDao
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    routing {
        planetRout()
    }
}

fun Route.planetRout() {
    val planetDao: PlanetDao by inject()

    route("/v1/planets") {
        get("/") {
            call.respond(planetDao.getPlanets())
        }
    }
}
