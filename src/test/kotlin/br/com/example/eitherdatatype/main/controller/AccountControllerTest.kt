package br.com.example.eitherdatatype.main.controller

import br.com.example.eitherdatatype.infrastructure.adapter.account.CreateAccountInputDTO
import com.google.gson.Gson
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class AccountControllerTest(@Autowired val mockMvc: MockMvc) {

    @Test
    fun shouldReturnStatus200WhenCreate() {
        val data = CreateAccountInputDTO("Any Name", "any@email.com")
        mockMvc.perform(post("/poc/v1/account").
        content(Gson().toJson(data)).
        contentType(MediaType.APPLICATION_JSON).
        accept(MediaType.APPLICATION_JSON)).
        andExpect(status().isCreated)
    }

    @Test
    fun shouldReturnStatus400WhenNotHaveFieldEmail() {
        mockMvc.perform(post("/poc/v1/account").
        content("{\"name\":\"Any Name\"}").
        contentType(MediaType.APPLICATION_JSON).
        accept(MediaType.APPLICATION_JSON)).
        andExpect(status().isBadRequest)
    }

    @Test
    fun shouldReturnStatus400WhenNotHaveFieldName() {
        mockMvc.perform(post("/poc/v1/account").
        content("{\"email\":\"any@email.com\"}").
        contentType(MediaType.APPLICATION_JSON).
        accept(MediaType.APPLICATION_JSON)).
        andExpect(status().isBadRequest)
    }

    @Test
    fun shouldReturnStatus422WhenFieldEmailIsInvalid() {
        mockMvc.perform(post("/poc/v1/account").
        content("{\"email\":\"invalid-email.com\",\"name\":\"Any Name\"}").
        contentType(MediaType.APPLICATION_JSON).
        accept(MediaType.APPLICATION_JSON)).
        andExpect(status().isUnprocessableEntity)
    }

    @Test
    fun shouldReturnStatus422WhenFieldNameIsEmpty() {
        mockMvc.perform(post("/poc/v1/account").
        content("{\"email\":\"valid@email.com\",\"name\":\"\"}").
        contentType(MediaType.APPLICATION_JSON).
        accept(MediaType.APPLICATION_JSON)).
        andExpect(status().isUnprocessableEntity)
    }

}