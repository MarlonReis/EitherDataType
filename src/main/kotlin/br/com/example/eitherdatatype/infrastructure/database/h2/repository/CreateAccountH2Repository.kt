package br.com.example.eitherdatatype.infrastructure.database.h2.repository

import br.com.example.eitherdatatype.data.repository.CreateAccountRepository
import br.com.example.eitherdatatype.infrastructure.database.ConnectionDatabase
import br.com.example.eitherdatatype.inputboundary.CreateAccountInputBoundary
import br.com.example.eitherdatatype.shared.Either
import java.sql.Connection

class CreateAccountH2Repository(
        private val connectionDatabase: ConnectionDatabase<Connection>) : CreateAccountRepository {

    override fun createAccount(account: CreateAccountInputBoundary): Either<Unit> {
        val response = connectionDatabase.connection()
        if (response.isFailure) {
            return Either.failure(response.exceptionOrNull()!!)
        }
        val connection: Connection = response.getOrNull() as Connection

        try {
            val statement = connection.createStatement()
            val executionResponse = statement.executeUpdate("")
            if (executionResponse == 1) {
                return Either.success()
            }
        } catch (e: Exception) {
            return Either.failure(e)
        }
    }
}