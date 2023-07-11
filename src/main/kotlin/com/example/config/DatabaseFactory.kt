package com.example.config

import com.example.entity.PlanetEntity
import com.example.table.PlanetTable
import com.example.table.StarTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

object DatabaseFactory {

    fun init(configuration: Configuration) {
        Database.connect(hikari(configuration))
        createTables()
        transaction {
            PlanetTable.insert {
                it[name] = "Mars"
                it[size] = "100"
                it[distanceFromSun] = "100 km"
                it[description] = "Blah"
                it[createdTimestamp] = DateTime.now()
            }
        }
    }

    fun init() {
        Database.connect(hikari())
        createTables()
        transaction {
            PlanetTable.insert {
                it[name] = "Mars"
                it[size] = "100"
                it[distanceFromSun] = "100 km"
                it[description] = "Blah"
                it[createdTimestamp] = DateTime.now()
            }
        }
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
        transaction {
            SchemaUtils.create(PlanetTable)
            SchemaUtils.create(StarTable)
        }
    }
}