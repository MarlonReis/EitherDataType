package br.com.example.eitherdatatype.queue.kafka

import br.com.example.eitherdatatype.queue.protocol.ProducerMessage
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.annotation.DirtiesContext
import java.util.concurrent.TimeUnit

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(
    partitions = 1,
    brokerProperties = [
        "listeners=PLAINTEXT://localhost:9092",
        "port=9092"
    ]
)
internal class SimpleMessageConsumerTest {
    @Autowired
    private lateinit var producerMessage: ProducerMessage

    @Autowired
    private lateinit var consumer: SimpleMessageConsumer


    @Test
    fun shouldReceiveMessage() {
        producerMessage.send("AnyMessage")

        consumer.latch.await(10000, TimeUnit.MILLISECONDS);
        assertThat(consumer.latch.count, equalTo(0L));
    }

}