package ru.tnsk.backend.domain.model.account

data class FullUser(
    override val id: Int,
    override val login: String,
    override val name: String,
    val role: UserRole,
    val passwordHash: String
) : User(id, login, name) {
    fun asUser(): User = User(id, login, name)
}