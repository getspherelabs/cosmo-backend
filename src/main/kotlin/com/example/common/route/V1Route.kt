package com.example.common.route

import com.example.features.article.articleRoute
import com.example.features.planet.planetRoute
import com.example.features.star.starRoute
import io.ktor.server.routing.*

fun Route.v1() {
    route("/api/v1") {
        planetRoute()
        articleRoute()
        starRoute()
    }
}