package br.com.example.eitherdatatype.domain.usecase

import br.com.example.eitherdatatype.outputdata.AccountOutputData
import br.com.example.eitherdatatype.shared.Either

interface FindAccountByEmailUseCase {
    fun find(email: String): Either<AccountOutputData>
}