package br.com.example.eitherdatatype.domain.usecase

import br.com.example.eitherdatatype.inputboundary.CreateAccountInputBoundary
import br.com.example.eitherdatatype.shared.Either

interface CreateAccountUseCase {
    fun createAccount(account: CreateAccountInputBoundary): Either<Unit>
}