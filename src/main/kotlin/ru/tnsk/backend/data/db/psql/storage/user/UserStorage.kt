package ru.tnsk.backend.data.db.psql.storage.user

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import ru.tnsk.backend.data.db.psql.entity.UserEntity
import ru.tnsk.backend.data.db.psql.table.UsersTable

class UserStorage(
    private val db: Database
) {
    fun create(
        login: String,
        name: String,
        passwordHash: String
    ) = transaction(db) {
        UserEntity.new {
            this.login = login
            this.name = name
            this.passwordHash = passwordHash
        }.asUser()
    }

    fun findUserById(id: Int) = transaction(db) { UserEntity.findById(id)?.asUser() }

    fun findUserByLogin(login: String) = transaction(db) { findUserEntityByLogin(login)?.asUser() }

    fun findFullUserByLogin(login: String) = transaction(db) { findUserEntityByLogin(login)?.asFullUser() }

    private fun findUserEntityByLogin(login: String) = transaction(db) {
        UserEntity.find {
            UsersTable.login eq login
        }.singleOrNull()
    }
}