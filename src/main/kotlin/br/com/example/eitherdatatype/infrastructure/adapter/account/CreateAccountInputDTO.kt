package br.com.example.eitherdatatype.infrastructure.adapter.account

import br.com.example.eitherdatatype.infrastructure.exception.RequiredFieldException
import br.com.example.eitherdatatype.infrastructure.protocols.InputBoundaryAdapter
import br.com.example.eitherdatatype.inputboundary.CreateAccountInputBoundary
import br.com.example.eitherdatatype.shared.Either


class CreateAccountInputDTO(val name: String?, val email: String?) : InputBoundaryAdapter<CreateAccountInputBoundary> {
    override fun toInputBoundary(): Either<CreateAccountInputBoundary> {
        if (name == null) {
            return Either.failure(RequiredFieldException("name"))
        }
        if (email == null) {
            return Either.failure(RequiredFieldException("email"))
        }
        return Either.success(CreateAccountInputBoundary(name, email))
    }
}