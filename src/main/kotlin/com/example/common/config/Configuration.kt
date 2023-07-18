package com.example.common.config

import io.ktor.server.config.*

interface Configuration {
    val config: ApplicationConfig
    val host: String
    val port: String
    val name: String
    val user: String
    val password: String
    val driver: String
    val maxPoolSize: Int

    data class Builder(
        var config: ApplicationConfig? = null,
        var host: String = "",
        var port: String = "",
        var name: String = "",
        var user: String = "",
        var password: String = "",
        var driver: String = "",
        var maxPoolSize: Int = 0
    ) {
        inline fun build(
            block: Builder.() -> Unit
        ): Configuration {
            val builder = Builder().apply(block)

            return object : Configuration {
                override val config: ApplicationConfig = checkNotNull(builder.config) {
                    "Config is not initialized yet."
                }
                override val host: String = builder.host
                override val port: String = builder.port
                override val name: String = builder.name
                override val user: String = builder.user
                override val password: String = builder.password
                override val driver: String = builder.driver
                override val maxPoolSize: Int = builder.maxPoolSize
            }
        }
    }
}