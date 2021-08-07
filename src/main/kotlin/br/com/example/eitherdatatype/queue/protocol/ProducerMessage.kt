package br.com.example.eitherdatatype.queue.protocol

interface ProducerMessage {
    fun send(data: String)
}