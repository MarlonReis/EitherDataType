package br.com.example.eitherdatatype.infrastructure.exception

class RequiredFieldException(fieldName: String) : Exception("Field '$fieldName' is required!")