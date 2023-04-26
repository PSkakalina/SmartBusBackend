package ru.tnsk.backend.data.db.psql

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import ru.tnsk.backend.data.db.psql.table.*

@JvmInline
value class TnskDatabase(val db: Database) {
    companion object {
        fun connect(jdbcDatabaseUrl: String): TnskDatabase {
            return TnskDatabase(Database.connect(createHikariDS(jdbcDatabaseUrl)))
        }

        private fun createHikariDS(jdbcDatabaseUrl: String): HikariDataSource {
            val config = HikariConfig()
            with(config) {
                jdbcUrl = jdbcDatabaseUrl
                maximumPoolSize = 30
                isAutoCommit = false
                transactionIsolation = "TRANSACTION_REPEATABLE_READ"
                username = "postgres"
                password = "1234"
                validate()
            }

            return HikariDataSource(config)
        }
    }

    fun createTablesIfNeeded() {
        transaction(db) {
            SchemaUtils.createMissingTablesAndColumns(
                UsersTable,
                StopsTable,
                RoutesTable,
                TracesTable,
                RouteTraceTable
            )
        }
    }
}