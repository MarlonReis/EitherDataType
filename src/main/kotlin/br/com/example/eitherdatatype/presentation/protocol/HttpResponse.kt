package br.com.example.eitherdatatype.presentation.protocol

data class HttpResponse(val status: Int, val body: Any? = null)