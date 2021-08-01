package br.com.example.eitherdatatype.main.factories.account

import br.com.example.eitherdatatype.data.repository.CreateAccountRepository
import br.com.example.eitherdatatype.data.repository.EmailExistsRepository
import br.com.example.eitherdatatype.data.usecase.DbCreateAccountUseCase
import br.com.example.eitherdatatype.domain.usecase.CreateAccountUseCase
import br.com.example.eitherdatatype.infrastructure.adapter.Slf4jLogger
import br.com.example.eitherdatatype.infrastructure.database.ConnectionDatabase
import br.com.example.eitherdatatype.infrastructure.database.h2.repository.CreateAccountH2Repository
import br.com.example.eitherdatatype.infrastructure.database.h2.repository.EmailExistsH2Repository
import br.com.example.eitherdatatype.domain.data.input.CreateAccountInputData
import br.com.example.eitherdatatype.main.factories.LoggerDecorator
import br.com.example.eitherdatatype.presentation.controller.CreateAccountController
import br.com.example.eitherdatatype.presentation.protocol.Controller
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.sql.Connection

@Configuration
class UserAccountFactory {

    @Bean
    fun makeCreateAccountUseCaseFactory(
        createRepo: CreateAccountRepository,
        emailRepo: EmailExistsRepository
    ): CreateAccountUseCase {
        return DbCreateAccountUseCase(createRepo, emailRepo)
    }

    @Bean
    fun makeCreateAccountRepositoryFactory(connection: ConnectionDatabase<Connection>): CreateAccountRepository {
        return CreateAccountH2Repository(connection)
    }

    @Bean
    fun makeEmailExistsRepositoryFactory(connection: ConnectionDatabase<Connection>): EmailExistsRepository {
        return EmailExistsH2Repository(connection)
    }

    @Bean
    fun makeCreateAccountControllerFactory(usecase: CreateAccountUseCase): Controller<CreateAccountInputData> {
        return LoggerDecorator(logger = Slf4jLogger.build(), controller = CreateAccountController(usecase))
    }


}