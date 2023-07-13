package com.example.di

import com.example.features.article.ArticleController
import com.example.features.article.DefaultArticleController
import com.example.features.planet.DefaultPlanetController
import com.example.features.planet.PlanetController
import org.koin.dsl.module

val controllerModule = module {
    single<PlanetController> { DefaultPlanetController(get()) }
    single<ArticleController> { DefaultArticleController(get()) }

}