package br.com.example.eitherdatatype.queue.kafka

import br.com.example.eitherdatatype.domain.data.input.CreateAccountInputData
import br.com.example.eitherdatatype.domain.usecase.CreateAccountUseCase
import br.com.example.eitherdatatype.queue.protocol.ConsumerListener
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class SimpleMessageConsumer(@Autowired private val usecase: CreateAccountUseCase) : ConsumerListener {

    @KafkaListener(topics = ["SIMPLE_MESSAGE"], groupId = "kafka-poc")
    override fun consumer(@Payload data: String) {
        println("consumer: $data")
        val inputData = ObjectMapper().readValue(data, CreateAccountInputData::class.java)
        usecase.createAccount(inputData)

    }
}