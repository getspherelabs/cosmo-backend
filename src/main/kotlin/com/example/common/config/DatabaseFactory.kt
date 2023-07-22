package com.example.common.config

import com.example.common.extension.prepareTables
import com.example.data.table.tables
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

object DatabaseFactory {

    fun withPostgres(config: ApplicationConfig) {
        Database.connect(hikariWithPostgres(config))
        createTables()
        migration(hikariWithPostgres(config))
    }

    fun withH2() {
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

    private fun migration(hikari: HikariDataSource) {
        val flyway = Flyway.configure()
            .dataSource(hikari)
            .baselineOnMigrate(true)
            .load()

        try {
            flyway.info()
            flyway.migrate()
        } catch (e: Exception) {
            throw e
        }
    }

    private fun hikariWithPostgres(databaseConfig: ApplicationConfig): HikariDataSource {
        val hikariConfiguration = createHikariConfiguration(databaseConfig)
        return HikariDataSource(hikariConfiguration)
    }

    private fun createHikariConfiguration(databaseConfig: ApplicationConfig): HikariConfig {
        val host = databaseConfig.property("database.host").getString()
        val name = databaseConfig.property("database.name").getString()
        val user = databaseConfig.property("database.username").getString()
        val password = databaseConfig.property("database.password").getString()
        val port = databaseConfig.property("database.port").getString().toInt()
        val driver = databaseConfig.property("database.driver").getString()
        val maxPoolSize = databaseConfig.property("database.poolSize").getString().toInt()
        return configureDataSource(host, port, name, user, password, driver, maxPoolSize)
    }

    private fun configureDataSource(
        host: String,
        port: Int,
        name: String,
        user: String,
        password: String,
        driver: String,
        maxPoolSize: Int
    ): HikariConfig {
        val hikariConfiguration = HikariConfig()
        hikariConfiguration.driverClassName = driver
        hikariConfiguration.jdbcUrl = "jdbc:postgresql://$host:$port/$name"
        hikariConfiguration.username = user
        hikariConfiguration.password = password
        hikariConfiguration.isAutoCommit = false
        hikariConfiguration.maximumPoolSize = maxPoolSize
        hikariConfiguration.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        hikariConfiguration.validate()

        return hikariConfiguration
    }

    private fun createTables() {
        prepareTables(*tables.toTypedArray())
    }

}