package com.example.di

import com.example.dao.DefaultPlanetDao
import com.example.dao.DefaultStarDao
import com.example.dao.PlanetDao
import com.example.dao.StarDao
import org.koin.dsl.module

val daoModule = module {
    single<PlanetDao> { DefaultPlanetDao() }
    single<StarDao> { DefaultStarDao() }
}