package com.example.common.config

import com.example.common.extension.prepareTables
import com.example.data.table.tables
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init(configuration: Configuration) {
        Database.connect(hikari(configuration))
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

    private fun hikari(configuration: Configuration): HikariDataSource {
        val hikariConfig = HikariConfig().apply {
            driverClassName = "org.h2.Driver"
            password = configuration.password
            jdbcUrl = "jdbc:h2:mem:test"
            maximumPoolSize = configuration.maxPoolSize
            username = configuration.user
            validate()
        }

        return HikariDataSource(hikariConfig)
    }

    private fun createTables() {
        prepareTables(*tables.toTypedArray())
    }
}