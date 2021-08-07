package br.com.example.eitherdatatype.queue.kafka

import br.com.example.eitherdatatype.queue.protocol.ConsumerListener
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.annotation.DirtiesContext

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(
    partitions = 1,
    brokerProperties = [
        "listeners=PLAINTEXT://localhost:9092",
        "port=9092"]
)
internal class SimpleMessageConsumerTest(
    @Autowired val consumer: SimpleMessageConsumer,
    @Autowired val producer: KafkaTemplate<String, String>,
    //@Value("\${kafka.topic}")
    val topic: String = "SIMPLE_MESSAGE"
) {

    @BeforeEach
    fun setUp() {

    }

    @Test
    fun consumer() {
        producer.send(topic,"Any message")
        println("----------------------")
        println(topic)
        assertTrue(true)
    }
}