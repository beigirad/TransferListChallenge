package ir.beigirad.challenge.common

sealed class ViewResource<out T> {
    object NotAvailable : ViewResource<Nothing>()
    class Loading<T> : ViewResource<T>()
    data class Success<T>(val data: T) : ViewResource<T>()
    data class Failure<T> constructor(val error: Error) : ViewResource<T>()

    fun asError(): Failure<*>? = this as? Failure
    fun asOk(): Success<out T>? = this as? Success
}