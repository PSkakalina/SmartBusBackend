package ru.tnsk.backend.data.db.mappers

import ru.tnsk.backend.data.db.psql.entity.UserEntity
import ru.tnsk.backend.domain.model.account.FullUser
import ru.tnsk.backend.domain.model.account.User

fun UserEntity.asUser() = User(
    id.value,
    login,
    name
)

fun UserEntity.asFullUser() = FullUser(
    id.value,
    login,
    name,
    role,
    passwordHash
)