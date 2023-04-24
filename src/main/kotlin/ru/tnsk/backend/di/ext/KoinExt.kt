package ru.tnsk.backend.di.ext

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.ktor.ext.get


/**
 * Retrieve given dependency for KoinComponent
 * @param qualifier - bean name / optional
 * @param parameters
 */
inline fun <reified T : Any> ApplicationCall.koinGet(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = get<T>(qualifier, parameters)


/**
 * Retrieve given dependency for KoinComponent
 * @param qualifier - bean name / optional
 * @param parameters
 */
inline fun <reified T : Any> Application.koinGet(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = get<T>(qualifier, parameters)


/**
 * Retrieve given dependency for KoinComponent
 * @param qualifier - bean name / optional
 * @param parameters
 */
inline fun <reified T : Any> Route.koinGet(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = get<T>(qualifier, parameters)

/**
 * Retrieve given dependency for KoinComponent
 * @param qualifier - bean name / optional
 * @param parameters
 */
inline fun <reified T : Any> Routing.koinGet(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = get<T>(qualifier, parameters)
