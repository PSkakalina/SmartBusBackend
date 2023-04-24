package ru.tnsk.backend.data.repository

import ru.tnsk.backend.data.db.psql.storage.user.UserStorage
import ru.tnsk.backend.domain.model.account.FullUser
import ru.tnsk.backend.domain.model.account.User
import ru.tnsk.backend.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userStorage: UserStorage
) : UserRepository {
    override fun createUser(
        login: String,
        name: String,
        passwordHash: String
    ) = userStorage.create(login, name, passwordHash)

    override fun getUser(login: String) = userStorage.findUserByLogin(login)
    override fun getUser(id: Int): User? = userStorage.findUserById(id)
    override fun getFullUser(login: String): FullUser? = userStorage.findFullUserByLogin(login)
}