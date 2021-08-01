package br.com.example.eitherdatatype.data.repository

import br.com.example.eitherdatatype.domain.entity.UserAccount
import br.com.example.eitherdatatype.shared.Either

interface CreateAccountRepository {
    fun createAccount(account: UserAccount): Either<Unit>
}