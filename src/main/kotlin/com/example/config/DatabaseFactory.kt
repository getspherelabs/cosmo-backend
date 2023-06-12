package com.example.config

import com.example.table.PlanetTable
import com.example.table.StarTable
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

    private fun hikari(configuration: Configuration): HikariDataSource {
        val hikariConfig = HikariConfig().apply {
            driverClassName = configuration.driver
            password = configuration.password
            jdbcUrl = "jdbc:postgresql://${configuration.host}:${configuration.port}"
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