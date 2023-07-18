package com.example

import com.example.common.config.DatabaseFactory
import com.example.common.plugins.configureKoin
import com.example.common.plugins.configureRouting
import com.example.common.plugins.configureSerialization
import com.example.common.plugins.configureSwagger
import io.ktor.server.application.*
import com.example.plugins.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureKoin()
    configureSerialization()
    configureRouting()
    configureCors()
    configureSwagger()
    val config = environment.config
    DatabaseFactory.initWithPostgres(config)
}

fun Application.testModule() {
    configureKoin()
    configureSerialization()
    configureRouting()
    configureCors()
    DatabaseFactory.init()
}