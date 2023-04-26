package ru.tnsk.backend.data.db.psql.table

import org.jetbrains.exposed.dao.id.IntIdTable
import ru.tnsk.backend.domain.model.account.UserRole

object UsersTable : IntIdTable("users") {
    val passwordHash = text("passwordHash")
    val login = text("login").uniqueIndex()
    val name = text("name")
    val role = enumeration<UserRole>("role").default(UserRole.DEFAULT)
}