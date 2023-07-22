package com.example

import com.example.common.config.DatabaseFactory
import com.example.common.plugins.configureKoin
import com.example.common.plugins.configureRouting
import com.example.common.plugins.configureSerialization
import com.example.common.plugins.configureSwagger
import io.ktor.server.application.*
import com.example.plugins.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureKoin()
    configureSerialization()
    configureRouting()
    configureCors()
    configureSwagger()
    val config = environment.config
    DatabaseFactory.withPostgres(config)
}

fun Application.testModule() {
    configureKoin()
    configureSerialization()
    configureRouting()
    configureCors()
    DatabaseFactory.withH2()
}