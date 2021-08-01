package br.com.example.eitherdatatype.infrastructure.protocols

import br.com.example.eitherdatatype.shared.Either

interface InputBoundaryAdapter<out T> {
    fun toInputBoundary(): Either<T>
}