package com.example.di

import com.example.features.article.ArticleRepository
import com.example.features.article.DefaultArticleRepository
import com.example.features.planet.DefaultPlanetRepository
import com.example.features.planet.PlanetRepository
import com.example.features.star.DefaultStarRepository
import com.example.features.star.StarRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ArticleRepository> { DefaultArticleRepository(get()) }
    single<PlanetRepository> { DefaultPlanetRepository(get()) }
    single<StarRepository> { DefaultStarRepository(get()) }
}