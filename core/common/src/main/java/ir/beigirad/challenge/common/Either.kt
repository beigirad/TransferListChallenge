package ir.beigirad.challenge.common

/**
 * Created by Farhad Beigirad on 6/24/23.
 */
sealed class Either<out A> {
    data class Success<A>(val value: A) : Either<A>()
    data class Failure<A>(val error: Error) : Either<A>()
}