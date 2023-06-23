package ir.beigirad.challenge.common

sealed class Error {
    data class Exception(val throwable: Throwable) : Error()
    data class Message(val message: CharSequence) : Error()
}

fun Error.asString(): String =
    when (this) {
        is Error.Exception -> this.throwable.message.orEmpty()
        is Error.Message -> this.message.toString()
    }