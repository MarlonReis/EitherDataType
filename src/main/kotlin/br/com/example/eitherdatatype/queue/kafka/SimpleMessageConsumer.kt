package br.com.example.eitherdatatype.queue.kafka

import br.com.example.eitherdatatype.queue.protocol.ConsumerListener
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch

@Component
class SimpleMessageConsumer : ConsumerListener {
    val latch = CountDownLatch(1);

    @KafkaListener(topics = ["SIMPLE_MESSAGE"], groupId = "kafka-poc")
    override fun consumer(@Payload data: String) {
        println("consumer: $data")
        latch.countDown();
    }
}