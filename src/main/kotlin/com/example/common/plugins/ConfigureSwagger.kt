package com.example.plugins

import io.github.smiley4.ktorswaggerui.SwaggerUI
import io.ktor.server.application.*

fun Application.configureSwagger() {
    install(SwaggerUI) {
        swagger {
            swaggerUrl = "swagger-ui"
            forwardRoot = true
        }
        info {
            title = "Cosmo API"
            version = "1.0"
            description = "Swagger for CosmoAPI"
        }
        server {
            url = "http://0.0.0.0:8080"
            description = "Local Server"
        }
    }
}