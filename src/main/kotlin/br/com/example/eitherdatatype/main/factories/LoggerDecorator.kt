package br.com.example.eitherdatatype.main.factories

import br.com.example.eitherdatatype.infrastructure.protocols.LoggerSystem
import br.com.example.eitherdatatype.outputboundary.MessageError
import br.com.example.eitherdatatype.presentation.protocol.Controller
import br.com.example.eitherdatatype.presentation.protocol.HttpRequest
import br.com.example.eitherdatatype.presentation.protocol.HttpResponse

class LoggerDecorator<T>(
    private val controller: Controller<T>,
    private val logger: LoggerSystem,
) : Controller<T> {
    override fun handle(request: HttpRequest<T>): HttpResponse {
        val response = controller.handle(request)

        if (response.status != 200) {
            when (response.body) {
                is Exception -> logger.error(controller.javaClass, response.body.message!!)
                is MessageError -> logger.error(controller.javaClass, response.body.toString())
            }

        }
        return response
    }
}