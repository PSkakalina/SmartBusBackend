package ru.tnsk.backend.data.db.psql.table

import org.jetbrains.exposed.dao.id.IntIdTable

object StopsTable : IntIdTable("stops") {
    val originalId = integer("original_id").uniqueIndex()
    val len = integer("len") //WTF
    val name = text("name")
}