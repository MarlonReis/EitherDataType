package br.com.example.eitherdatatype.infrastructure.database

import br.com.example.eitherdatatype.infrastructure.exception.CannotConnectWithDatabaseException
import br.com.example.eitherdatatype.shared.Either
import java.sql.Connection
import java.sql.DriverManager

class H2DatabaseConnection(
    private val url: String,
    private val user: String,
    private val password: String
) : ConnectionDatabase<Connection> {
    private var connectionDatabase: Either<Connection> = Either.failure(CannotConnectWithDatabaseException())

    init {
        Class.forName("org.h2.Driver");
    }

    override fun connection(): Either<Connection> {
        try {
            val connection = DriverManager.getConnection(url, user, password)
            if (!connection.isClosed) {
                connectionDatabase = Either.success(connection)
                createTable()
                return connectionDatabase
            }
        } catch (e: Exception) {
            return Either.failure(e)
        }
        return Either.failure(CannotConnectWithDatabaseException())
    }

    private fun createTable() {
        if (connectionDatabase.isSuccess) {
            val connection = connectionDatabase.getOrNull()!!
            connection.createStatement().execute(
                "CREATE TEMPORARY TABLE IF NOT EXISTS account(id IDENTITY NOT NULL PRIMARY KEY,nome VARCHAR, email VARCHAR)"
            )
        }
    }

    override fun close() {
        if (connectionDatabase.isSuccess) {
            val response = (connectionDatabase.value as Connection)
            if (!response.isClosed) {
                response.close()
            }
            connectionDatabase = Either.failure(CannotConnectWithDatabaseException())
        }
    }

}