package br.com.example.eitherdatatype.main.factories

import br.com.example.eitherdatatype.infrastructure.database.ConnectionDatabase
import br.com.example.eitherdatatype.infrastructure.database.H2DatabaseConnection
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseConnectionFactory {
    @Value("\${database.url}")
    private lateinit var url: String

    @Value("\${database.user}")
    private lateinit var user: String

    @Value("\${database.password}")
    private lateinit var password: String

    @Bean
    fun makeFactory(): ConnectionDatabase<*> {
        return H2DatabaseConnection(url, user, password)
    }
}