package br.com.example.eitherdatatype.main.controller

import br.com.example.eitherdatatype.infrastructure.adapter.ControllerAdapter
import br.com.example.eitherdatatype.infrastructure.adapter.account.CreateAccountInputDTO
import br.com.example.eitherdatatype.inputboundary.CreateAccountInputBoundary
import br.com.example.eitherdatatype.presentation.protocol.Controller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/poc/v1/account")
class AccountController(@Autowired val controller: Controller<CreateAccountInputBoundary>) {


    @PostMapping
    fun createAccount(@RequestBody accountData: CreateAccountInputDTO): ResponseEntity<Any> {
        return ControllerAdapter.command(controller, accountData)
    }
}