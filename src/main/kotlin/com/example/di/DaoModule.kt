package com.example.di

import com.example.dao.DefaultPlanetDao
import com.example.dao.DefaultStarDao
import com.example.dao.PlanetDao
import com.example.dao.StarDao
import com.example.data.dao.ArticleDao
import com.example.data.dao.DefaultArticleDao
import org.koin.dsl.module

val daoModule = module {
    single<PlanetDao> { DefaultPlanetDao() }
    single<StarDao> { DefaultStarDao() }
}

val testModule = module {
    single<ArticleDao> { DefaultArticleDao() }
    single<com.example.data.dao.PlanetDao> { com.example.data.dao.DefaultPlanetDao() }
}