package br.com.example.eitherdatatype.infrastructure.database.h2.repository

import br.com.example.eitherdatatype.domain.entity.UserAccount
import br.com.example.eitherdatatype.infrastructure.database.H2DatabaseConnection
import br.com.example.eitherdatatype.infrastructure.database.exception.CannotCreateAccountException
import br.com.example.eitherdatatype.inputboundary.CreateAccountInputBoundary
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

internal class CreateAccountH2RepositoryTest {
    private val createAccountData = UserAccount.create(
        name = "Any Name",
        email = "any@email.com"
    )

    @Test
    fun shouldReturnSuccessWhenTheAccountIsCreated() {
        val databaseConnection =  H2DatabaseConnection("jdbc:h2:~/AnyDatabase", "any", "AnyPassword")
        val connection = databaseConnection.connection().getOrNull()!!

        val sut = CreateAccountH2Repository(databaseConnection)
        val response = sut.createAccount(createAccountData)
        assertTrue(response.isSuccess)
        connection.close()
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