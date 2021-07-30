package br.com.example.eitherdatatype.data.repository

import br.com.example.eitherdatatype.inputboundary.CreateAccountInputBoundary
import br.com.example.eitherdatatype.shared.Either

interface CreateAccountRepository {
    fun createAccount(account: CreateAccountInputBoundary): Either<Unit>
}