package ru.tnsk.backend.data.db.psql.storage

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import ru.tnsk.backend.data.db.mappers.asFullUser
import ru.tnsk.backend.data.db.psql.entity.UserEntity
import ru.tnsk.backend.data.db.psql.table.UsersTable
import ru.tnsk.backend.domain.model.account.UserRole

class UserStorage(
    private val db: Database
) {
    fun create(
        login: String,
        name: String,
        passwordHash: String,
        role: UserRole = UserRole.DEFAULT
    ) = transaction(db) {
        UserEntity.new {
            this.login = login
            this.name = name
            this.role = role
            this.passwordHash = passwordHash
        }
    }.asFullUser()

    fun findUserById(id: Int) = transaction(db) { UserEntity.findById(id) }?.asFullUser()

    fun findUserByLogin(login: String) = transaction(db) {
        UserEntity.find {
            UsersTable.login eq login
        }.singleOrNull()?.asFullUser()
    }

    fun findUserRole(id: Int): UserRole? = transaction(db) {
        UserEntity.findById(id)?.role
    }
}