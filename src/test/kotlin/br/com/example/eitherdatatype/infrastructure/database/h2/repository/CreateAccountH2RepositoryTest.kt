package br.com.example.eitherdatatype.infrastructure.database.h2.repository

import br.com.example.eitherdatatype.infrastructure.database.H2DatabaseConnection
import br.com.example.eitherdatatype.infrastructure.database.exception.CannotCreateAccountException
import br.com.example.eitherdatatype.inputboundary.CreateAccountInputBoundary
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

internal class CreateAccountH2RepositoryTest {
    private val createAccountData = CreateAccountInputBoundary(
        name = "Any Name",
        email = "any@email.com"
    )
    val databaseConnection = H2DatabaseConnection("jdbc:h2:~/AnyDatabase", "any", "AnyPassword")

    @AfterEach
    fun setDown() {
        databaseConnection.close()
    }

    @Test
    fun shouldReturnSuccessWhenTheAccountIsCreated() {

        val connection = databaseConnection.connection().getOrNull()!!
        connection.createStatement().execute(
                "CREATE TEMPORARY TABLE IF NOT EXISTS account(id IDENTITY NOT NULL PRIMARY KEY,nome VARCHAR, email VARCHAR)"
            )

        val sut = CreateAccountH2Repository(databaseConnection)
        val response = sut.createAccount(createAccountData)
        assertTrue(response.isSuccess)
        connection.close()
    }

    @Test
    fun shouldReturnFailureWhenCannotInsertNewRegister() {
        val h2Db = H2DatabaseConnection("jdbc:h2:~/AnyDatabase", "any", "AnyPassword")
        val sut = CreateAccountH2Repository(h2Db)

        val response = sut.createAccount(createAccountData)
        assertTrue(response.isFailure)

        val exception = assertThrows(CannotCreateAccountException::class.java) {
            throw response.exceptionOrNull()!!
        }

        assertNotNull(exception.cause)
    }

    @Test
    fun shouldReturnFailureWhenNotHaveConnection() {
        val h2Db = H2DatabaseConnection("", "", "")
        val sut = CreateAccountH2Repository(h2Db)

        val response = sut.createAccount(createAccountData)
        assertTrue(response.isFailure)
        assertNotNull(response.exceptionOrNull())

        h2Db.close()
    }

}