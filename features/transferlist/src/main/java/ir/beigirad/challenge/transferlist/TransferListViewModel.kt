package ir.beigirad.challenge.transferlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.beigirad.challenge.common.Price
import ir.beigirad.challenge.common.ViewResource
import ir.beigirad.challenge.model.TransactionEntity
import ir.beigirad.challenge.model.TransactionType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import kotlin.random.Random

/**
 * Created by Farhad Beigirad on 6/23/23.
 */
class TransferListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TransferListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchBalance()
        fetchFakeTransaction()
    }

    private fun fetchBalance() {
        viewModelScope.launch {
            _uiState.update { it.copy(balance = ViewResource.Loading()) }
            delay(1000)
            _uiState.update { it.copy(balance = ViewResource.Success(Price(78500000u))) }
        }
    }

    private fun fetchFakeTransaction() {
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
            calendar.add(Calendar.DAY_OF_YEAR, Random.nextInt(-8, -1))
            calendar.add(Calendar.SECOND, Random.nextInt(-16000, -1000))
            TransactionEntity(
                id = it.toULong(),
                title = title,
                type = transactionType,
                date = calendar.time,
                amount = Price((Random.nextFloat() * 10_000).toUInt() * 1000u)
            )
        }

        _uiState.update { it.copy(transactions = ViewResource.Loading()) }
        viewModelScope.launch {
            delay(2000)
            _uiState.update { it.copy(transactions = ViewResource.Success(list)) }
        }
    }
}