package com.example.utils

import com.example.http.Response
import com.example.http.response.PlanetIdResponse
import com.example.http.response.PlanetResponse
import com.example.http.response.PlanetsResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.PolymorphicModuleBuilder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

val module = SerializersModule {
    polymorphic(Response::class) {
        subclass(PlanetIdResponse::class)
        subclass(PlanetsResponse::class)
    }

}
val json = Json {
    ignoreUnknownKeys = true
    serializersModule = module
}

inline fun <reified T> T.toJson(): String = json.encodeToString(this)

inline fun <reified T> String?.toDto(): T = this!!.let { newResult -> json.decodeFromString(newResult) }