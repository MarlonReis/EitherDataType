package br.com.example.eitherdatatype.queue.kafka

import br.com.example.eitherdatatype.domain.usecase.CreateAccountUseCase
import br.com.example.eitherdatatype.queue.protocol.ProducerMessage
import com.ninjasquad.springmockk.MockkBean
import io.mockk.MockKAnnotations
import io.mockk.confirmVerified
import io.mockk.verify
import org.junit.Before
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.annotation.DirtiesContext
import reactor.core.publisher.Mono.delay
import java.time.Duration


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
    @MockkBean
    lateinit var usecase: CreateAccountUseCase

    @Autowired
    private lateinit var producerMessage: ProducerMessage

    @MockkBean
    private lateinit var consumer: SimpleMessageConsumer


    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)


    @Test
    fun shouldReceiveMessage() {
        val data = AccountData("Any Name", "email@exemple.com")
        producerMessage.send(data = data.toString())

        //delay(Duration.ofSeconds(1))
        Thread.sleep( 2 000)

//        verify { usecase.createAccount(CreateAccountInputData("Any Name", "email@exemple.com")) }
        verify { consumer.consumer(data = data.toString()) }
        confirmVerified(data.toString())
    }

    private data class AccountData(
        val name: String,
        val email: String
    ) {
        override fun toString(): String {
            return "{\"name\":\"$name\",\"email\":\"$email\"}"
        }
    }

}