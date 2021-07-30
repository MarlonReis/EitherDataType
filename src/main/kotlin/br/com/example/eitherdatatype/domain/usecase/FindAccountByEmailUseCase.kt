package br.com.example.eitherdatatype.domain.usecase

import br.com.example.eitherdatatype.outputboundary.AccountOutputBoundary
import br.com.example.eitherdatatype.shared.Either

interface FindAccountByEmailUseCase {
    fun find(email: String): Either<AccountOutputBoundary>
}