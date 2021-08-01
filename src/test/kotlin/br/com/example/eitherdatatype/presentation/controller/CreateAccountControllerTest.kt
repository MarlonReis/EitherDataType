package br.com.example.eitherdatatype.presentation.controller

import br.com.example.eitherdatatype.data.exceptions.EmailIsBeingUsedException
import br.com.example.eitherdatatype.domain.usecase.CreateAccountUseCase
import br.com.example.eitherdatatype.inputdata.CreateAccountInputData
import br.com.example.eitherdatatype.presentation.protocol.HttpRequest
import br.com.example.eitherdatatype.shared.Either
import io.mockk.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.hasProperty
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CreateAccountControllerTest {

    @Test
    fun shouldReceivedCorrectParams() {
        val data = CreateAccountInputData("Any Name", "any@email.com.br")

        val createAccountStub = spyk(CreateAccountUseCaseStub())
        val sut = CreateAccountController(createAccountStub)
        sut.handle(HttpRequest(body = data))

        verify { createAccountStub.createAccount(data) }
        confirmVerified(createAccountStub)
    }

    @Test
    fun shouldReturnHttpStatusCode422WhenNameFieldIsInvalid() {
        val data = CreateAccountInputData("", "any@email.com.br")

        val createAccountStub = CreateAccountUseCaseStub()
        val sut = CreateAccountController(createAccountStub)
        val response = sut.handle(HttpRequest(body = data))

        assertEquals(response.status, 422)
        assertThat(response.body, hasProperty("message", `is`("'name' attribute is required!")))
        assertThat(response.body, hasProperty("field", `is`("name")))
        assertThat(response.body, hasProperty("value", `is`("")))
    }

    @Test
    fun shouldReturnHttpStatusCode422WhenEmailFieldIsInvalid() {
        val data = CreateAccountInputData("Any Name", "any-email.com.br")

        val createAccountStub = CreateAccountUseCaseStub()
        val sut = CreateAccountController(createAccountStub)
        val response = sut.handle(HttpRequest(body = data))

        assertEquals(response.status, 422)
        assertThat(response.body, hasProperty("message", `is`("'email' attribute is invalid!")))
        assertThat(response.body, hasProperty("field", `is`("email")))
        assertThat(response.body, hasProperty("value", `is`("any-email.com.br")))
    }

    @Test
    fun shouldReturnHttpStatusCode200WhenCreateRegisterWithSuccess() {
        val data = CreateAccountInputData("Any Name", "any@email.com.br")

        val createAccountStub = CreateAccountUseCaseStub()
        val sut = CreateAccountController(createAccountStub)
        val response = sut.handle(HttpRequest(body = data))
        assertEquals(response.status, 201)
    }

    @Test
    fun shouldReturnHttpStatusCode400WhenUseCaseReturnFailure() {
        val data = CreateAccountInputData("Any Name", "any@email.com.br")
        val createAccountStub = mockk<CreateAccountUseCaseStub>(relaxed = true)

        every { createAccountStub.createAccount(any()) } returns Either.failure(EmailIsBeingUsedException())

        val sut = CreateAccountController(createAccountStub)
        val response = sut.handle(HttpRequest(body = data))
        assertEquals(response.status, 422)
    }


    private class CreateAccountUseCaseStub : CreateAccountUseCase {
        override fun createAccount(account: CreateAccountInputData): Either<Unit> {
            return Either.success(Unit)
        }
    }
}