package ir.beigirad.challenge.model

import ir.beigirad.challenge.common.Price
import java.util.Date

/**
 * Created by Farhad Beigirad on 6/23/23.
 */
data class TransactionEntity(
    val id: ULong,
    val title: String,
    val type: TransactionType,
    val date: Date,
    val amount: Price,
)