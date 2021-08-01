package br.com.example.eitherdatatype.main.controller

import br.com.example.eitherdatatype.infrastructure.adapter.account.CreateAccountInputDTO
import com.google.gson.Gson
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class AccountControllerTest(
    @Autowired val testRestTemplate: TestRestTemplate,
    @Autowired val mockMvc: MockMvc
) {

    @Test
    fun shouldReturnStatus200WhenCreate() {
        val data = CreateAccountInputDTO("Any Name", "any@email.com")
        mockMvc.perform(post("/poc/v1/account").
        content(Gson().toJson(data)).
        contentType(MediaType.APPLICATION_JSON).
        accept(MediaType.APPLICATION_JSON)).
        andExpect(status().isCreated)
    }

}