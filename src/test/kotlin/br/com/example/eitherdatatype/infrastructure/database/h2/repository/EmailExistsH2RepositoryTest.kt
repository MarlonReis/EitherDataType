package br.com.example.eitherdatatype.infrastructure.database.h2.repository

import br.com.example.eitherdatatype.infrastructure.database.H2DatabaseConnection
import br.com.example.eitherdatatype.infrastructure.database.exception.CannotFoundAccountException
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

internal class EmailExistsH2RepositoryTest {
    var databaseConnection = H2DatabaseConnection("jdbc:h2:~/${UUID.randomUUID()}", "any", "AnyPassword")


    @AfterEach
    fun setDown() {
        databaseConnection.close()

    }

    @Test
    fun shouldReturnSuccessWithTrueWhenFoundRegister() {
        val connection = databaseConnection.connection().getOrNull()!!
        connection.createStatement()
            .execute("CREATE TEMPORARY TABLE IF NOT EXISTS account(id IDENTITY NOT NULL PRIMARY KEY,nome VARCHAR, email VARCHAR)")


        connection.createStatement().execute(" INSERT INTO account(nome,email) values ('Any Name','any@email.com')")

        val sut = EmailExistsH2Repository(databaseConnection)
        val response = sut.exist("any@email.com")

        assertTrue(response.isSuccess)
        assertTrue(response.value is Boolean)
        assertTrue(response.getOrNull()!!)
        connection.close()
    }

    @Test
    fun shouldReturnSuccessWithFalseWhenNotFoundRegister() {
        val sut = EmailExistsH2Repository(databaseConnection)
        val response = sut.exist("any@email.com")

        assertTrue(response.isSuccess)
        assertFalse(response.getOrNull()!!)
    }


    @Test
    fun shouldReturnFailureWhenNotHaveDatabaseConnection() {
        val databaseConnection = H2DatabaseConnection("", "", "")
        val sut = EmailExistsH2Repository(databaseConnection)
        val response = sut.exist("any@email.com")

        assertTrue(response.isFailure)
        assertNotNull(response.exceptionOrNull())
    }
}