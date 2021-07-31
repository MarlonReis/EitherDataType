package br.com.example.eitherdatatype.presentation.protocol

interface Controller<T> {
    fun handle(request: HttpRequest<T>): HttpResponse
}