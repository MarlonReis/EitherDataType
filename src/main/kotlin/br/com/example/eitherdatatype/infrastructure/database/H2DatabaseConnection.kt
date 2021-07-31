package br.com.example.eitherdatatype.infrastructure.database

import br.com.example.eitherdatatype.infrastructure.database.exception.CannotConnectWithDatabase
import br.com.example.eitherdatatype.shared.Either
import java.sql.Connection
import java.sql.DriverManager

class H2DatabaseConnection(
    private val url: String,
    private val user: String,
    private val password: String
) : ConnectionDatabase<Connection> {
    private var connectionDatabase: Either<Connection> = Either.failure(CannotConnectWithDatabase())

    override fun connection(): Either<Connection> {
        try {
            val connection = DriverManager.getConnection(url, user, password)
            if (!connection.isClosed) {
                connectionDatabase = Either.success(connection)
                return connectionDatabase
            }
        } catch (e: Exception) {
            return Either.failure(e)
        }
        return Either.failure(CannotConnectWithDatabase())
    }

    override fun close() {
        if (connectionDatabase.isSuccess) {
            val response = (connectionDatabase.value as Connection)
            if (!response.isClosed) {
                response.close()
            }
            connectionDatabase = Either.failure(CannotConnectWithDatabase())
        }
    }
}