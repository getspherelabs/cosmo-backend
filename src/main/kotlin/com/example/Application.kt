package com.example

import com.example.config.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*


fun main() {
    embeddedServer(Netty, port = 8080, host = "localhost", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureKoin()
    configureSerialization()
    configureRouting()
    configureCors()
    configureSwagger()
    DatabaseFactory.init()
}