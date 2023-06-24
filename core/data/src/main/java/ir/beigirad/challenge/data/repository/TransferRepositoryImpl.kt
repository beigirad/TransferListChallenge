package ir.beigirad.challenge.data.repository

import ir.beigirad.challenge.common.Either
import ir.beigirad.challenge.common.Price
import ir.beigirad.challenge.data.datasource.FakeDataSource
import ir.beigirad.challenge.model.TransactionEntity
import javax.inject.Inject

/**
 * Created by Farhad Beigirad on 6/24/23.
 */
class TransferRepositoryImpl @Inject constructor(
    private val dataSource: FakeDataSource
) : TransferRepository {
    override suspend fun getBalance(): Either<Price> = dataSource.getRandomBalance()

    override suspend fun getAll(): Either<List<TransactionEntity>> = dataSource.getRandomTransfers(100)
}