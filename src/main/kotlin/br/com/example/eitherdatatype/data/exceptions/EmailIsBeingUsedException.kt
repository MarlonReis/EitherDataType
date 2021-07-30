package br.com.example.eitherdatatype.data.exceptions

class EmailIsBeingUsedException(message: String = "Email is being used on another account!") : Exception(message)