package br.com.example.eitherdatatype.infrastructure.protocols

import br.com.example.eitherdatatype.shared.Either

interface InputDataAdapter<out T> {
    fun toInputData(): Either<T>
}