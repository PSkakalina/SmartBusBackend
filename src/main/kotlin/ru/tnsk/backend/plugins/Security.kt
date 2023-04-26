package ru.tnsk.backend.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import ru.tnsk.backend.core.utils.checkNotNulls
import ru.tnsk.backend.domain.model.account.User
import java.time.ZonedDateTime
import java.util.*


private const val ID = "id"
private const val NAME = "name"
private const val LOGIN = "login"

fun Application.configureSecurity() {
    install(Authentication) {
        jwt {
            verifier(JwtConfig.verifier)
            realm = "tech.blur"
            validate {
                val id = it.payload.getClaim(ID).asInt()
                val name = it.payload.getClaim(NAME).asString()
                val login = it.payload.getClaim(LOGIN).asString()
                if (checkNotNulls(id, login, name)) User(id, login, name)
                else null
            }
        }
    }
}

object JwtConfig {
    private const val secret = "OZ6RJPQdzYNha78szaLT"
    private const val issuer = "tnsk.ru"
    private val algorithm = Algorithm.HMAC512(secret)
    private val expiration: Date
        get() = Date.from(ZonedDateTime.now().plusDays(3650).toInstant())

    val verifier: JWTVerifier = JWT.require(algorithm).withIssuer(issuer).build()

    fun generateToken(user: User): String =
        JWT
            .create()
            .withSubject("Authentication")
            .withIssuer(issuer)
            .withExpiresAt(expiration)
            .withClaim(ID, user.id)
            .withClaim(NAME, user.login)
            .withClaim(LOGIN, user.name)
            .sign(algorithm)
}

