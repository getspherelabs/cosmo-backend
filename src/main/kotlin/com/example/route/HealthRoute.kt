package com.example.route

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.check() {
    route("/") {
        get("/health_check") {
            call.respondText("Server is working")
        }
    }

}