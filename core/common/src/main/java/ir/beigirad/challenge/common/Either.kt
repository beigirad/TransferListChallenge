package ir.beigirad.challenge.common

/**
 * Created by Farhad Beigirad on 6/24/23.
 *
 * A generic class that can be used in all layers and places that we don't have loading and other status
 *
 * @see [ViewResource]
 * @see [PaginationViewResource]
 */
sealed class Either<out A> {
    data class Success<A>(val value: A) : Either<A>()
    data class Failure<A>(val error: Error) : Either<A>()
}