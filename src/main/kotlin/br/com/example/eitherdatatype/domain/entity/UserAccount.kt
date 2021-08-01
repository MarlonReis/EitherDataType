package br.com.example.eitherdatatype.domain.entity

import java.util.*

class UserAccount private constructor(val id: Int?, val name: String, val email: String, val createOn: Date = Date()) {

    companion object {
        fun create(name: String, email: String): UserAccount {
            return UserAccount(id = null, name = name, email = email)
        }
    }

}