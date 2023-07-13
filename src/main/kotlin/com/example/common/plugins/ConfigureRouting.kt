package com.example.common.plugins

import com.example.common.route.check
import com.example.common.route.v1
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
