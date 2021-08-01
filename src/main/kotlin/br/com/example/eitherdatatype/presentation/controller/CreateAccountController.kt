package br.com.example.eitherdatatype.presentation.controller

import br.com.example.eitherdatatype.data.exceptions.EmailIsBeingUsedException
import br.com.example.eitherdatatype.domain.usecase.CreateAccountUseCase
import br.com.example.eitherdatatype.inputboundary.CreateAccountInputBoundary
import br.com.example.eitherdatatype.outputboundary.MessageError
import br.com.example.eitherdatatype.presentation.protocol.Controller
import br.com.example.eitherdatatype.presentation.protocol.HttpRequest
import br.com.example.eitherdatatype.presentation.protocol.HttpResponse

class CreateAccountController(private val usecase: CreateAccountUseCase) : Controller<CreateAccountInputBoundary> {
    override fun handle(request: HttpRequest<CreateAccountInputBoundary>): HttpResponse {
        if (request.body!!.name.isEmpty()) {
            val body = MessageError.build("'name' attribute is required!", "name", request.body.name)
            return HttpResponse(422, body)
        }

        if (!request.body.email.contains("@")) {
            val body = MessageError.build("'email' attribute is invalid!", "email", request.body.email)
            return HttpResponse(422, body)
        }

        val response = usecase.createAccount(request.body)

        if (response.isSuccess) {
            return HttpResponse(status = 200)
        }

        if (response.exceptionOrNull() is EmailIsBeingUsedException) {
            val ex = response.exceptionOrNull()!!
            return HttpResponse(422, MessageError.build(ex.message!!, "email", request.body.email))
        }

        return HttpResponse(500, response.exceptionOrNull())
    }
}