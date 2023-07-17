package com.example.core

import com.example.data.table.tables
import com.example.module
import com.example.testModule
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import io.ktor.server.testing.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Before
import org.koin.test.KoinTest
import kotlin.test.AfterTest

open class CosmoTest: KoinTest {

    @Before
    fun setup() {
        Database.connect(hikari())

        transaction {
            SchemaUtils.createMissingTablesAndColumns(*tables.toTypedArray())
        }
    }

    @AfterTest
    fun teardown() {
        transaction {
            SchemaUtils.drop(*tables.toTypedArray())
        }
    }

    protected fun withApp(test: suspend TestApplicationEngine.() -> Unit) {
        withTestApplication(Application::testModule) {
            runBlocking {
                test()
            }
        }
    }

    /**
     *  Hikari is a JDBC connection pool.
     *  It is used to keep connections open for re-use
     */

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:mem:test"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        return HikariDataSource(config)
    }

}