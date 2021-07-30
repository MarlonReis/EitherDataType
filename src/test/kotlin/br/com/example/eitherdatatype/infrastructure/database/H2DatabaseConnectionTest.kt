package br.com.example.eitherdatatype.infrastructure.database

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.sql.SQLException

internal class H2DatabaseConnectionTest {

    @Test
    fun shouldReturnConnectionWhenItSuccess() {
        val sut = H2DatabaseConnection("jdbc:h2:~/AnyDatabaseName", "any", "AnyPassword")
        val response = sut.connection()

        assertTrue(response.isSuccess)
        assertNotNull(response.getOrNull())
        assertFalse(response.getOrNull()!!.isClosed)
    }

    @Test
    fun shouldReturnFailureWhenCanNotConnectWithDatabase() {
        val sut = H2DatabaseConnection("#%", "", "")
        val response = sut.connection()
        assertTrue(response.isFailure)
        assertEquals(response.exceptionOrNull()!!::class, SQLException::class)
    }


}