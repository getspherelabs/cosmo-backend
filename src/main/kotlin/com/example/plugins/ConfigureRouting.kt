package com.example.plugins

import com.example.route.check
import com.example.route.v1
import io.ktor.server.routing.*
import io.ktor.server.application.*


fun Application.configureRouting() {
    routing {
        v1()
    }
    routing {
        check()
    }
}
