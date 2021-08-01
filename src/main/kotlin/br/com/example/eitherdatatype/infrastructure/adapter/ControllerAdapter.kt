package br.com.example.eitherdatatype.infrastructure.adapter


import br.com.example.eitherdatatype.infrastructure.protocols.InputBoundaryAdapter
import br.com.example.eitherdatatype.outputboundary.MessageError
import br.com.example.eitherdatatype.presentation.protocol.Controller
import br.com.example.eitherdatatype.presentation.protocol.HttpRequest
import br.com.example.eitherdatatype.presentation.protocol.HttpResponse
import org.springframework.http.ResponseEntity

class ControllerAdapter<T> private constructor(private val controller: Controller<T>) : Controller<T> {

    companion object {
        fun <T> command(controller: Controller<T>, data: InputBoundaryAdapter<T>): ResponseEntity<Any> {
            val responseData = data.toInputBoundary()
            if (responseData.isSuccess) {
                val response = ControllerAdapter(controller).handle(HttpRequest(body = responseData.getOrNull()))
                return ResponseEntity.status(response.status).body(response.body)
            }
            val message: String = responseData.exceptionOrNull().let {
                it?.message ?: ""
            }
            return ResponseEntity.status(400).body(MessageError.build(message))
        }
    }

    override fun handle(request: HttpRequest<T>): HttpResponse {
        val response = controller.handle(request)
        return when (response.status) {
            200, 201, 400, 422 -> HttpResponse(response.status, response.body)
            else -> {
                HttpResponse(500, MessageError.build("Internal Server Error"))
            }
        }
    }


}