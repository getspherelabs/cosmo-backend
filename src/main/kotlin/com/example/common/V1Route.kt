package com.example.common

import com.example.features.article.articleRoute
import com.example.features.planet.planetRoute
import io.ktor.server.routing.*

fun Route.v1() {
    route("/api/v1") {
        planetRoute()
        articleRoute()
    }
}