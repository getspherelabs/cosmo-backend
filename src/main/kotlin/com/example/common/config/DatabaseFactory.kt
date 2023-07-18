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

        val hikariConfiguration = HikariConfig()
        val host = databaseConfig.property("database.host").getString()
        val name = databaseConfig.property("database.name").getString()
        val user = databaseConfig.property("database.username").getString()
        val password = databaseConfig.property("database.password").getString()
        val port = databaseConfig.property("database.port").getString().toInt()

        hikariConfiguration.driverClassName = "org.postgresql.ds.PGSimpleDataSource"
        hikariConfiguration.jdbcUrl = "jdbc:postgresql://$host:$password:$port/$name"
        hikariConfiguration.username = user
        hikariConfiguration.isAutoCommit = false
        hikariConfiguration.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        hikariConfiguration.validate()
        return HikariDataSource(hikariConfiguration)
    }

    private fun createTables() {
        prepareTables(*tables.toTypedArray())
    }
}