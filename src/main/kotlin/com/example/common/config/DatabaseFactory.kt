package com.example.common.config

import com.example.common.extension.prepareTables
import com.example.data.table.tables
import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import org.jetbrains.exposed.sql.Database

object DatabaseFactory {

    fun initWithPostgres(config: ApplicationConfig) {
        Database.connect(hikariWithPostgres(config))
        createTables()
    }

    fun init() {
        Database.connect(hikari())
        createTables()
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:mem:test"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

    private fun hikariWithPostgres(databaseConfig: ApplicationConfig): HikariDataSource {
        val config = HikariConfig()
        val host = databaseConfig.property("ktor.database.host").getString()
        val name = databaseConfig.property("ktor.database.name").getString()
        val user = databaseConfig.property("ktor.database.user").getString()
        val password = databaseConfig.property("ktor.database.password").getString()
        val port = databaseConfig.property("ktor.database.port").getString()

        config.driverClassName = databaseConfig.property("ktor.database.driver").getString()
        config.jdbcUrl = "jdbc:postgresql://$host:$port/$name"
        config.password = password
        config.username = user
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

    private fun createTables() {
        prepareTables(*tables.toTypedArray())
    }
}