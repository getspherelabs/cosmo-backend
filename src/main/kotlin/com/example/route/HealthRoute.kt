package com.example.route

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.check() {
    get("/health_check") {
        // Checks the database
        call.respondText("Database is working")
    }
}