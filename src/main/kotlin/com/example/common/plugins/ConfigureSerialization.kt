package com.example.plugins

import com.example.common.http.Response
import com.example.features.planet.PlanetIdResponse
import com.example.features.planet.PlanetResponse
import com.example.features.planet.PlanetsResponse
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.application.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

val module = SerializersModule {
    polymorphic(Response::class) {
        subclass(PlanetIdResponse::class)
        subclass(PlanetsResponse::class)
        subclass(PlanetResponse::class)
    }
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(
            json = Json {
                ignoreUnknownKeys = true
                encodeDefaults = true
                serializersModule = module

                prettyPrint = true
            },
            contentType = ContentType.Application.Json
        )
    }
}

val DefaultJson: Json = Json {
    encodeDefaults = true
    serializersModule = module
    ignoreUnknownKeys = true
    prettyPrint = true
}
