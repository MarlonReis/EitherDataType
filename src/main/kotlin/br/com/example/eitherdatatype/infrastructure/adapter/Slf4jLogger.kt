package br.com.example.eitherdatatype.infrastructure.adapter

import br.com.example.eitherdatatype.infrastructure.protocols.LoggerSystem
import org.slf4j.LoggerFactory


class Slf4jLogger : LoggerSystem {

    override fun error(origin: Class<*>, message: String) {
        LoggerFactory.getLogger(origin).error(message)
    }

    companion object {
        fun build(): Slf4jLogger {
            return Slf4jLogger()
        }
    }
}


