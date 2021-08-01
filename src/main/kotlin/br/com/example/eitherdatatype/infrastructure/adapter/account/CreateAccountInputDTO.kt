package br.com.example.eitherdatatype.infrastructure.adapter.account

import br.com.example.eitherdatatype.infrastructure.protocols.InputBoundaryAdapter
import br.com.example.eitherdatatype.inputboundary.CreateAccountInputBoundary

class CreateAccountInputDTO(val name: String, val email: String) : InputBoundaryAdapter<CreateAccountInputBoundary> {
    override fun toInputBoundary(): CreateAccountInputBoundary {
        return CreateAccountInputBoundary(name, email)
    }
}