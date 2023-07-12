package com.example.di

import com.example.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single<ArticleRepository> { DefaultArticleRepository(get()) }
    single<PlanetRepository> { DefaultPlanetRepository(get()) }
    single<StarRepository> { DefaultStarRepository(get()) }
}