package ru.tnsk.backend.data.db.psql.storage.stop

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import ru.tnsk.backend.data.db.psql.entity.StopEntity
import ru.tnsk.backend.data.db.psql.table.StopsTable

class StopStorage(private val db: Database) {
    fun create(
        originalId: Int,
        name: String,
        len: Int
    ) = transaction(db) {
        StopEntity.new {
            this.originalId = originalId
            this.name = name
            this.len = len
        }.asStop()
    }

    fun createOrGet(
        originalId: Int,
        name: String,
        len: Int
    ) = transaction(db) {
        val entity = StopEntity.find {
            StopsTable.originalId eq originalId
        }.firstOrNull() ?: StopEntity.new {
            this.originalId = originalId
            this.name = name
            this.len = len
        }

        entity.asStop()
    }
}