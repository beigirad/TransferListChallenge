package ir.beigirad.challenge.common

sealed class Error {
    data class Exception(val throwable: Throwable) : Error()
    data class Message(val message: CharSequence) : Error()
}
