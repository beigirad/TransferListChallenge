package ir.beigirad.challenge.data.repository

import ir.beigirad.challenge.common.Either
import ir.beigirad.challenge.common.Error
import ir.beigirad.challenge.common.Price
import ir.beigirad.challenge.model.TransactionEntity
import ir.beigirad.challenge.model.TransactionType
import kotlinx.coroutines.delay
import java.util.Calendar
import javax.inject.Inject
import kotlin.random.Random

/**
 * Created by Farhad Beigirad on 6/24/23.
 */
class TransferRepositoryImpl @Inject constructor() : TransferRepository {
    override suspend fun getBalance(): Either<Price> {
        delay(1000)
        return Either.Success(Price(87500000u))
    }

    override suspend fun getAll(): Either<List<TransactionEntity>> {
        if (Random.nextBoolean()) return Either.Failure(Error.Message("یه چی به فنا رفته..."))

        delay(2000)
        val transactionTypeTitle = listOf(
            TransactionType.Remittance to "انتقال به سپرده",
            TransactionType.Remittance to "انتقال پل",
            TransactionType.Atm to "برداشت از کارت",
            TransactionType.DepositGift to "هدیه دعوت از دوستان",
            TransactionType.Fee to "کارمزد انتقال",
        )

        val calendar = Calendar.getInstance()

        val list = List(100) {
            val (transactionType, title) = transactionTypeTitle.random()
            calendar.add(Calendar.DAY_OF_YEAR, -Random.nextInt(1, 8))
            calendar.add(Calendar.SECOND, -Random.nextInt(1000, 16000))
            TransactionEntity(
                id = it.toULong(),
                title = title,
                type = transactionType,
                date = calendar.time,
                amount = Price((Random.nextFloat() * 10_000).toUInt() * 1000u)
            )
        }

        return Either.Success(list)
    }
}