package ir.beigirad.challenge.common

/**
 * * Created by Farhad Beigirad on 6/23/23.
 *
 * This class is used for UI states
 */
sealed class ViewResource<out T> {
    object NotAvailable : ViewResource<Nothing>()
    class Loading<T> : ViewResource<T>()
    data class Success<T>(val data: T) : ViewResource<T>()
    data class Failure<T> constructor(val error: Error) : ViewResource<T>()

    fun asError(): Failure<*>? = this as? Failure
    fun asOk(): Success<out T>? = this as? Success
}