package com.example.di

import com.example.data.dao.*
import org.koin.dsl.module

val daoModule = module {
    single<PlanetDao> { DefaultPlanetDao() }
    single<StarDao> { DefaultStarDao() }
    single<ArticleDao> { DefaultArticleDao() }
}
