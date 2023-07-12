package com.example.route

import io.ktor.server.routing.*

fun Route.v1() {
    route("/api/v1") {
        planetRoute()
    }
}