package ru.tnsk.backend.data.db.psql.table

import org.jetbrains.exposed.dao.id.IntIdTable

object UsersTable : IntIdTable("users") {
    val passwordHash = text("passwordHash")
    val login = text("login").uniqueIndex()
    val name = text("name")
}