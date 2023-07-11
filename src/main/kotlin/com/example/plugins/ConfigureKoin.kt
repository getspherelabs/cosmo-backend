package com.example.plugins

import com.example.di.controllerModule
import com.example.di.daoModule
import com.example.di.testModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(listOf(controllerModule, daoModule, testModule))
    }
}