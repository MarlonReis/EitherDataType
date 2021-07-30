package br.com.example.eitherdatatype.presentation.protocol

interface Controller {
    fun handle(request: HttpRequest): HttpResponse
}