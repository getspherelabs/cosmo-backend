package com.example.common.route

import io.github.smiley4.ktorswaggerui.dsl.get
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.check() {
    route("") {
        get("/health_check", {
            description = "Check the health server"
            response {
                default {
                    description = "Health response"
                }
                HttpStatusCode.OK to {
                    description = "Server working"
                    body<String> { description = "Cosmo"}
                }
            }
        }) {
            call.respondText("Server is working")
        }
    }

}