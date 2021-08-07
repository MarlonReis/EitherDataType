package br.com.example.eitherdatatype.main.factories.queue

import br.com.example.eitherdatatype.queue.kafka.SimpleMessageConsumer
import br.com.example.eitherdatatype.queue.protocol.ConsumerListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka

@EnableKafka
@Configuration
class KafkaListenerFactory {

    @Bean
    fun makeConsumerListener(): ConsumerListener {
        return SimpleMessageConsumer()
    }
}