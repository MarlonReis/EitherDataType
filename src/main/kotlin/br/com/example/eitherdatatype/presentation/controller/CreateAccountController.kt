package br.com.example.eitherdatatype.presentation.controller

import br.com.example.eitherdatatype.presentation.protocol.Controller
import br.com.example.eitherdatatype.presentation.protocol.HttpRequest
import br.com.example.eitherdatatype.presentation.protocol.HttpResponse

class CreateAccountController : Controller {
    override fun handle(request: HttpRequest): HttpResponse {
        return HttpResponse(status = 200)
    }
}