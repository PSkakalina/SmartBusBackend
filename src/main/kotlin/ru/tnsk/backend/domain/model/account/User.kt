package ru.tnsk.backend.domain.model.account

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable

@Serializable
open class User(
    open val id: Int,
    open val login: String,
    open val name: String,
) : Principal {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false

        if (id != other.id) return false
        if (login != other.login) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + login.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

    override fun toString(): String {
        return "User(id=$id, login='$login', name='$name')"
    }
}
