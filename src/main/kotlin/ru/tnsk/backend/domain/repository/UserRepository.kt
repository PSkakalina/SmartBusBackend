package ru.tnsk.backend.domain.repository

import ru.tnsk.backend.domain.model.account.FullUser
import ru.tnsk.backend.domain.model.account.User
import ru.tnsk.backend.domain.model.account.UserRole

interface UserRepository {
    fun createUser(
        login: String,
        name: String,
        passwordHash: String
    ): User

    fun getUser(login: String): User?


    fun getUser(id: Int): User?


    fun getFullUser(login: String): FullUser?

    fun getUserRole(id: Int): UserRole?
}