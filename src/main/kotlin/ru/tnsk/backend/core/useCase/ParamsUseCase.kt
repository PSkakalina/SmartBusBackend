package ru.tnsk.backend.core.useCase

abstract class ParamsUseCase<Input, Output> {
    suspend fun execute(params: Input): Output {
        return run(params)
    }

    protected abstract suspend fun run(params: Input): Output
}