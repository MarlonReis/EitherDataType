package br.com.example.eitherdatatype.queue.kafka

import br.com.example.eitherdatatype.queue.protocol.ConsumerListener
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class SimpleMessageConsumer : ConsumerListener {

    @KafkaListener(topics = ["\${kafka.topic}"])
    override fun consumer(data: String) {
        println("consumer: $data")
    }
}