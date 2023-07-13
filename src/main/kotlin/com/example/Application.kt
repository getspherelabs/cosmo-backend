package com.example

import com.example.common.config.DatabaseFactory
import com.example.common.plugins.configureKoin
import com.example.common.plugins.configureRouting
import com.example.common.plugins.configureSerialization
import com.example.common.plugins.configureSwagger
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