package ir.beigirad.challenge.data.repository

import ir.beigirad.challenge.common.Either
import ir.beigirad.challenge.common.Price
import ir.beigirad.challenge.model.TransactionEntity

/**
 * Created by Farhad Beigirad on 6/24/23.
 */
interface TransferRepository {
    suspend fun getBalance(): Either<Price>
    suspend fun getTransactions(page: Int, count: Int): Either<List<TransactionEntity>>
}


