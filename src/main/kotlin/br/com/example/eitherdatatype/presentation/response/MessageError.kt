package br.com.example.eitherdatatype.presentation.response

class MessageError private constructor(
    val message: String,
    var field: String? = null,
    var value: Any? = null
) {


    companion object {
        fun build(message: String, field: String, value: Any): MessageError {
            return MessageError(message, field, value)
        }

        fun build(msg: String): Any {
            return object {
                val message = msg
            }
        }

    }

    override fun toString(): String {
        return "'field':$field, 'message': $message, 'value': $value"
    }
}
