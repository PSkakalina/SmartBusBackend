package ru.tnsk.backend.data.db.psql.entity

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.tnsk.backend.data.db.psql.table.UsersTable

class UserEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserEntity>(UsersTable)

    var passwordHash by UsersTable.passwordHash
    var login by UsersTable.login
    var name by UsersTable.name
    var role by UsersTable.role
}