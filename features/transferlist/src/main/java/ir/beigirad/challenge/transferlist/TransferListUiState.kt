package ir.beigirad.challenge.transferlist

import ir.beigirad.challenge.common.PaginationViewResource
import ir.beigirad.challenge.common.Price
import ir.beigirad.challenge.common.ViewResource
import ir.beigirad.challenge.model.TransactionEntity

/**
 * Created by Farhad Beigirad on 6/23/23.
 */
data class TransferListUiState(
    val transactions: PaginationViewResource<TransactionEntity> = PaginationViewResource.NotAvailable(),
    val balance: ViewResource<Price> = ViewResource.NotAvailable,
)