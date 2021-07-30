package br.com.example.eitherdatatype.infrastructure.database.h2.repository

import br.com.example.eitherdatatype.infrastructure.database.H2DatabaseConnection
import br.com.example.eitherdatatype.inputboundary.CreateAccountInputBoundary
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CreateAccountH2RepositoryTest {


    @Test
    fun shouldReturnSuccessWhenTheAccountIsCreated() {
        val connection = H2DatabaseConnection("jdbc:h2:~/AnyDatabaseName", "any", "AnyPassword")
        val sut = CreateAccountH2Repository(connection)
        val data = CreateAccountInputBoundary(
                name = "Any Name",
                email = "any@email.com"
        )
        val response = sut.createAccount(data)
        assertTrue(response.isSuccess)
    }
}