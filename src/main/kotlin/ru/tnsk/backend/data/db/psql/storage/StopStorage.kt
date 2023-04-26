package ru.tnsk.backend.data.db.psql.storage

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import ru.tnsk.backend.data.db.mappers.asStop
import ru.tnsk.backend.data.db.psql.entity.StopEntity
import ru.tnsk.backend.data.db.psql.table.StopsTable

class StopStorage(private val db: Database) {
    fun create(
        originalId: Int,
        name: String,
        lat: Double,
        lng: Double,
        len: Int
    ) = transaction(db) {
        StopEntity.new {
            this.originalId = originalId
            this.name = name
            this.lat = lat
            this.lng = lng
            this.len = len
        }
    }.asStop()

    fun createOrGet(
        originalId: Int,
        name: String,
        lat: Double,
        lng: Double,
        len: Int
    ) = transaction(db) {
        StopEntity.find {
            StopsTable.originalId eq originalId
        }.firstOrNull()?.asStop() ?: create(originalId, name, lat, lng, len)
    }
}