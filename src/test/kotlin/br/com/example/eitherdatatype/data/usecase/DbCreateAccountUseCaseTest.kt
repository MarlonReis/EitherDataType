package br.com.example.eitherdatatype.data.usecase

import br.com.example.eitherdatatype.data.exceptions.EmailIsBeingUsedException
import br.com.example.eitherdatatype.data.repository.CreateAccountRepository
import br.com.example.eitherdatatype.data.repository.EmailExistsRepository
import br.com.example.eitherdatatype.domain.entity.UserAccount
import br.com.example.eitherdatatype.inputdata.CreateAccountInputData
import br.com.example.eitherdatatype.shared.Either
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


internal class DbCreateAccountUseCaseTest {
    private var createAccount: CreateAccountRepository = CreateAccountRepositoryStub()
    private val emailExists: EmailExistsRepository = EmailExistsRepositoryStub()
    private val createAccountDataParams = CreateAccountInputData(
        name = "Any Name",
        email = "valid@email.com"
    )

    @Test
    fun shouldCreateAccountWithSuccess() {
        val sut = DbCreateAccountUseCase(createAccount, emailExists)
        val response = sut.createAccount(createAccountDataParams)
        assertTrue(response.isSuccess)
    }

    @Test
    fun shouldReturnFailureWhenEmailExistsRepositoryThrowsExceptions() {
        val emailExists = mockk<EmailExistsRepositoryStub>(relaxed = true)
        every { emailExists.exist(any()) } returns Either.failure(Exception("Any message"))

        val sut = DbCreateAccountUseCase(createAccount, emailExists)
        val response = sut.createAccount(createAccountDataParams)
        assertTrue(response.isFailure)
        assertFalse(response.isSuccess)
        assertEquals(response.exceptionOrNull()!!.message, "Any message")
    }


    @Test
    fun shouldReturnFailureWhenRepositoryReturnTrue() {
        val emailExists = mockk<EmailExistsRepositoryStub>(relaxed = true)
        every { emailExists.exist(any()) } returns Either.success(true)

        val sut = DbCreateAccountUseCase(createAccount, emailExists)
        val response = sut.createAccount(createAccountDataParams)

        assertTrue(response.isFailure)
        assertFalse(response.isSuccess)
        assertEquals(response.exceptionOrNull()!!.javaClass, EmailIsBeingUsedException().javaClass)
    }

    @Test
    fun shouldReturnSuccessWhenRepositoryReturnSuccess() {
        val emailExists = mockk<EmailExistsRepositoryStub>(relaxed = true)
        every { emailExists.exist(any()) } returns Either.success(true)

        val sut = DbCreateAccountUseCase(createAccount, emailExists)
        val response = sut.createAccount(createAccountDataParams)

        assertTrue(response.isFailure)
        assertFalse(response.isSuccess)
        assertEquals(response.exceptionOrNull()!!.javaClass, EmailIsBeingUsedException().javaClass)
    }


    @Test
    fun shouldReturnExceptionWhenRepositoryReturnException() {
        val sut = DbCreateAccountUseCase(createAccount, emailExists)
        val response = sut.createAccount(createAccountDataParams)
        assertTrue(response.isSuccess)
    }


    private class CreateAccountRepositoryStub : CreateAccountRepository {
        override fun createAccount(account: UserAccount): Either<Unit> {
            return Either.success(Unit)
        }
    }

   private class EmailExistsRepositoryStub : EmailExistsRepository {
        override fun exist(email: String): Either<Boolean> {
            return Either.success(false)
        }
    }
}