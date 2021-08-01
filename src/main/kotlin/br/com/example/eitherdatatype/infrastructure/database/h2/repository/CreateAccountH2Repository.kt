package br.com.example.eitherdatatype.infrastructure.database.h2.repository

import br.com.example.eitherdatatype.data.repository.CreateAccountRepository
import br.com.example.eitherdatatype.domain.entity.UserAccount
import br.com.example.eitherdatatype.infrastructure.database.ConnectionDatabase
import br.com.example.eitherdatatype.infrastructure.database.exception.CannotCreateAccountException
import br.com.example.eitherdatatype.inputboundary.CreateAccountInputBoundary
import br.com.example.eitherdatatype.shared.Either
import java.sql.Connection

class CreateAccountH2Repository(
    private val connectionDatabase: ConnectionDatabase<Connection>
) : CreateAccountRepository {

    override fun createAccount(account: UserAccount): Either<Unit> {
        val response = connectionDatabase.connection()
        if (response.isFailure) {
            return Either.failure(response.exceptionOrNull()!!)
        }
        val connection: Connection = response.getOrNull() as Connection

        try {
            val statement = connection.createStatement()
            val executionResponse = statement.executeUpdate(
                "INSERT INTO account(nome,email) values ('${account.name}','${account.email}')"
            )

            if (executionResponse == 1) {
                return Either.success(Unit)
            }
            return Either.failure(
                CannotCreateAccountException(
                    "Cannot create register with name=${account.name} and email=${account.email}"
                )
            )
        } catch (e: Exception) {
            return Either.failure(CannotCreateAccountException(message = e.message, cause = e))
        }
    }
}