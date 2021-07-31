package br.com.example.eitherdatatype.infrastructure.database.h2.repository

import br.com.example.eitherdatatype.data.repository.EmailExistsRepository
import br.com.example.eitherdatatype.infrastructure.database.ConnectionDatabase
import br.com.example.eitherdatatype.infrastructure.database.exception.CannotCreateAccountException
import br.com.example.eitherdatatype.infrastructure.database.exception.CannotFoundAccountException
import br.com.example.eitherdatatype.shared.Either
import java.sql.Connection

class EmailExistsH2Repository(
    private val connectionDatabase: ConnectionDatabase<Connection>
) : EmailExistsRepository {
    override fun exist(email: String): Either<Boolean> {
        val response = connectionDatabase.connection()
        if (response.isFailure) {
            return Either.failure(response.exceptionOrNull()!!)
        }
        val connection: Connection = response.getOrNull() as Connection

        return try {
            val statement = connection.createStatement()
            val sql = "SELECT id FROM account WHERE email='$email'"
            Either.success(statement.executeQuery(sql).first())
        } catch (e: Exception) {
            Either.failure(CannotFoundAccountException(e))
        }
    }
}