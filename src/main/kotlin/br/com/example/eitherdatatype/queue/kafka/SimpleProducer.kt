package br.com.example.eitherdatatype.queue.kafka

import br.com.example.eitherdatatype.queue.protocol.ProducerMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class SimpleProducer(@Autowired private val template: KafkaTemplate<String, String>) : ProducerMessage {
    override fun send(data: String) {
        println("SEND DATA: $data")
        template.send("SIMPLE_MESSAGE", data)
    }
}