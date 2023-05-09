package ru.tnsk.backend.data.repository

import ru.tnsk.backend.data.db.psql.storage.DriverStorage
import ru.tnsk.backend.data.db.psql.storage.UserStorage
import ru.tnsk.backend.domain.model.account.FullUser
import ru.tnsk.backend.domain.model.account.User
import ru.tnsk.backend.domain.model.account.UserRole
import ru.tnsk.backend.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userStorage: UserStorage,
    private val driverStorage: DriverStorage
) : UserRepository {
    override fun createUser(
        login: String,
        name: String,
        passwordHash: String,
        userRole: UserRole
    ): User {
        val user = userStorage.create(login, name, passwordHash, userRole).asUser()

        if (userRole == UserRole.DRIVER) {
            driverStorage.create(user.id)
        }

        return user
    }

    override fun getUser(login: String) = userStorage.findUserByLogin(login)?.asUser()

    override fun getUser(id: Int): User? = userStorage.findUserById(id)?.asUser()

    override fun getFullUser(login: String): FullUser? = userStorage.findUserByLogin(login)

    override fun getFullUser(id: Int): FullUser? = userStorage.findUserById(id)

    override fun getUserRole(id: Int): UserRole? = userStorage.findUserRole(id)

    override fun getAllUsers(): List<FullUser> = userStorage.getAllUsers()
}