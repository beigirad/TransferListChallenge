package ir.beigirad.challenge.data.repository

import ir.beigirad.challenge.common.Either
import ir.beigirad.challenge.common.Price
import ir.beigirad.challenge.data.datasource.FakeDataSource
import ir.beigirad.challenge.model.TransactionEntity
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Farhad Beigirad on 6/24/23.
 */
class TransferRepositoryImpl @Inject constructor(
    private val dataSource: FakeDataSource
) : TransferRepository {
    override suspend fun getBalance(): Either<Price> =
        dataSource.getRandomBalance()
            .also { Timber.e("getBalance $it") }

    override suspend fun getTransactions(page: Int, count: Int): Either<List<TransactionEntity>> =
        dataSource.getRandomTransfers(page, count)
            .also { Timber.i("getTransactions page: $page, count:$count, result: $it") }
}