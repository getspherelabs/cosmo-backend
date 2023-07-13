package com.example.di

import com.example.features.article.ArticleController
import com.example.features.article.DefaultArticleController
import com.example.features.planet.DefaultPlanetController
import com.example.features.planet.PlanetController
import com.example.features.star.DefaultStarController
import com.example.features.star.StarController
import org.koin.dsl.module

val controllerModule = module {
    single<PlanetController> { DefaultPlanetController(get()) }
    single<ArticleController> { DefaultArticleController(get()) }
    single<StarController> { DefaultStarController(get()) }
}