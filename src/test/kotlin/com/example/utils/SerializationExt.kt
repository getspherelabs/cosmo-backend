package com.example.utils

import com.example.common.http.Response
import com.example.features.article.ArticleIdResponse
import com.example.features.article.ArticleResponse
import com.example.features.article.ArticlesResponse
import com.example.features.planet.PlanetIdResponse
import com.example.features.planet.PlanetResponse
import com.example.features.planet.PlanetsResponse
import com.example.features.star.StarIdResponse
import com.example.features.star.StarResponse
import com.example.features.star.StarsResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

val module = SerializersModule {
    polymorphic(Response::class) {
        subclass(PlanetIdResponse::class)
        subclass(PlanetsResponse::class)
        subclass(PlanetResponse::class)
        subclass(ArticlesResponse::class)
        subclass(ArticleIdResponse::class)
        subclass(ArticleResponse::class)
        subclass(StarsResponse::class)
        subclass(StarIdResponse::class)
        subclass(StarResponse::class)
    }

}
val json = Json {
    ignoreUnknownKeys = true
    serializersModule = module
}

inline fun <reified T> T.toJson(): String = json.encodeToString(this)

inline fun <reified T> String?.toDto(): T = this!!.let { newResult -> json.decodeFromString(newResult) }