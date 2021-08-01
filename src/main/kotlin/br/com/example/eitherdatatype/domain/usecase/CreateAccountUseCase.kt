package br.com.example.eitherdatatype.domain.usecase

import br.com.example.eitherdatatype.inputdata.CreateAccountInputData
import br.com.example.eitherdatatype.shared.Either

interface CreateAccountUseCase {
    fun createAccount(account: CreateAccountInputData): Either<Unit>
}