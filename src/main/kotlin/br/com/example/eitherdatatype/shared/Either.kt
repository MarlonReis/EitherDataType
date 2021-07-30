package br.com.example.eitherdatatype.shared

class Either<out T> constructor(val value: Any?) {
    val isSuccess: Boolean get() = value !is Failure
    val isFailure: Boolean get() = value is Failure

    inline fun getOrNull(): T? =
            when {
                isFailure -> null
                else -> value as T
            }

    fun exceptionOrNull(): Throwable? =
            when (value) {
                is Failure -> value.exception
                else -> null
            }


    override fun toString(): String =
            when (value) {
                is Failure -> value.toString()
                else -> "Success($value)"
            }


    companion object {
        @JvmName("success")
        fun <T> success(value: T): Either<T> = Either(value)


        @JvmName("failure")
        inline fun <T> failure(exception: Throwable): Either<T> = Either(createFailure(exception))
    }

    internal class Failure(@JvmField val exception: Throwable) {
        override fun equals(other: Any?): Boolean = other is Failure && exception == other.exception
        override fun hashCode(): Int = exception.hashCode()
        override fun toString(): String = "Failure($exception)"
    }
}

fun createFailure(exception: Throwable): Any = Either.Failure(exception)
