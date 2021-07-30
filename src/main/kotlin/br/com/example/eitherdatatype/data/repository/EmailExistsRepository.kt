package br.com.example.eitherdatatype.data.repository

import br.com.example.eitherdatatype.shared.Either

interface EmailExistsRepository {
    fun exist(email: String): Either<Boolean>
}