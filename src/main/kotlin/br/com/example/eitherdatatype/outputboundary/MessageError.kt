package br.com.example.eitherdatatype.outputboundary

class MessageError private constructor(
    val message: String,
    var field: String?,
    var value: Any?,
    val status: Int? = null
) {
    companion object {
        fun build(message: String, field: String, value: Any): MessageError {
            return MessageError(message, field, value)
        }
    }
}
