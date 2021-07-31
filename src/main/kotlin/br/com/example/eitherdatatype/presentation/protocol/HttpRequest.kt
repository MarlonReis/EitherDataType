package br.com.example.eitherdatatype.presentation.protocol

data class HttpRequest<D>(val body: D? = null, val params: Any? = null)