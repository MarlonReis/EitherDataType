package br.com.example.eitherdatatype.data.usecase

import br.com.example.eitherdatatype.data.exceptions.EmailIsBeingUsedException
import br.com.example.eitherdatatype.data.repository.CreateAccountRepository
import br.com.example.eitherdatatype.data.repository.EmailExistsRepository
import br.com.example.eitherdatatype.domain.entity.UserAccount
import br.com.example.eitherdatatype.domain.usecase.CreateAccountUseCase
import br.com.example.eitherdatatype.inputboundary.CreateAccountInputBoundary
import br.com.example.eitherdatatype.shared.Either


class DbCreateAccountUseCase(
    private val createAccountRepository: CreateAccountRepository,
    private val emailExistsRepository: EmailExistsRepository
) : CreateAccountUseCase {

    override fun createAccount(accountIb: CreateAccountInputBoundary): Either<Unit> {
        val emailExist = emailExistsRepository.exist(accountIb.email)
        if (emailExist.isFailure) {
            return Either.failure(emailExist.exceptionOrNull()!!)
        }

        if (emailExist.getOrNull() is Boolean && emailExist.getOrNull()!!) {
            return Either.failure(EmailIsBeingUsedException())
        }

        val account = UserAccount.create(accountIb.name, accountIb.email)
        return createAccountRepository.createAccount(account)
    }


}


