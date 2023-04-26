package ru.tnsk.backend.data.db.psql.entity

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.tnsk.backend.data.db.psql.table.StopsTable

class StopEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<StopEntity>(StopsTable)

    var originalId by StopsTable.originalId
    var len by StopsTable.len
    var name by StopsTable.name

    var lat by StopsTable.lat
    var lng by StopsTable.lng
}