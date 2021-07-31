package br.com.example.eitherdatatype.infrastructure.database

import br.com.example.eitherdatatype.shared.Either

interface ConnectionDatabase<out T> {
    fun connection(): Either<T>
    fun close()
}