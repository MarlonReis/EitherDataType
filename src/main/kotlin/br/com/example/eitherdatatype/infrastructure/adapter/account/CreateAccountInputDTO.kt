package br.com.example.eitherdatatype.infrastructure.adapter.account

import br.com.example.eitherdatatype.infrastructure.exception.RequiredFieldException
import br.com.example.eitherdatatype.infrastructure.protocols.InputDataAdapter
import br.com.example.eitherdatatype.domain.data.input.CreateAccountInputData
import br.com.example.eitherdatatype.shared.Either


class CreateAccountInputDTO(val name: String?, val email: String?) : InputDataAdapter<CreateAccountInputData> {
    override fun toInputData(): Either<CreateAccountInputData> {
        if (name == null) {
            return Either.failure(RequiredFieldException("name"))
        }
        if (email == null) {
            return Either.failure(RequiredFieldException("email"))
        }
        return Either.success(CreateAccountInputData(name, email))
    }
}