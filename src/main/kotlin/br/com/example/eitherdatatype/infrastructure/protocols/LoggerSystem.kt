package br.com.example.eitherdatatype.infrastructure.protocols

interface LoggerSystem {
    fun error(origin: Class<*>, message: String)
}