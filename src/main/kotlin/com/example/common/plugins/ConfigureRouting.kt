package com.example.plugins

import com.example.common.check
import com.example.common.v1
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
