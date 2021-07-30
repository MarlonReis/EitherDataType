package br.com.example.eitherdatatype.infrastructure.database

import br.com.example.eitherdatatype.infrastructure.database.error.CannotConnectWithDatabase
import br.com.example.eitherdatatype.shared.Either
import java.sql.Connection
import java.sql.DriverManager

class H2DatabaseConnection(
        val url: String,
        val user: String,
        val password: String
) : ConnectionDatabase<Connection> {

    override fun connection(): Either<Connection> {
        try {
            val connection = DriverManager.getConnection(url, user, password)
            if (!connection.isClosed) {
                return Either.success(connection)
            }
        } catch (e: Exception) {
            return Either.failure(e)
        }
        return Either.failure(CannotConnectWithDatabase())
    }
}