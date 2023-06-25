package ir.beigirad.challenge.common

/**
 * Created by Farhad Beigirad on 6/25/23.
 *
 * This class is a state holder that keep pagination state along the data.
 * Just [NotAvailable] contractor is public and others must re-instantiate with [loading], [appendData], [error] and [notAvailable].
 *
 * This limitation has been added to simplify and unify the usage of the class and also prevent any mutation/mistake in changing the state
 *
 * <code>
 * val pagination: PaginationViewResource<String> = PaginationViewResource.NotAvailable()
 * val paginationLoading : PaginationViewResource.Loading<String> = pagination.loading()
 * </code>
 */
sealed class PaginationViewResource<T>(
    val data: List<T>,
    val hasMore: Boolean,
    val page: Int
) {
    class Loading<T> internal constructor(data: List<T>, hasMore: Boolean, page: Int) :
        PaginationViewResource<T>(data, hasMore, page)

    class Success<T> internal constructor(data: List<T>, hasMore: Boolean, page: Int) :
        PaginationViewResource<T>(data, hasMore, page)

    class Failure<T> internal constructor(
        data: List<T>,
        hasMore: Boolean,
        page: Int,
        val error: Error
    ) : PaginationViewResource<T>(data, hasMore, page)

    class NotAvailable<T> : PaginationViewResource<T>(data = listOf(), hasMore = true, page = 0)

    fun loading(): PaginationViewResource<T> =
        Loading(data = data, hasMore = hasMore, page = page)

    fun appendData(newData: List<T>): PaginationViewResource<T> {
        val hasNewData = newData.isNotEmpty()
        return Success(
            data = data + newData,
            hasMore = hasNewData,
            page = if (hasNewData) page.inc() else page
        )
    }

    fun error(error: Error): PaginationViewResource<T> =
        Failure(error = error, data = data, hasMore = hasMore, page = page)

    fun notAvailable(): PaginationViewResource<T> =
        NotAvailable()
}