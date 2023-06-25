package ir.beigirad.challenge.transferlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.beigirad.challenge.common.Either
import ir.beigirad.challenge.common.PaginationViewResource
import ir.beigirad.challenge.common.ViewResource
import ir.beigirad.challenge.data.repository.TransferRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Farhad Beigirad on 6/23/23.
 */
@HiltViewModel
class TransferListViewModel @Inject constructor(
    private val repository: TransferRepository,
) : ViewModel() {
    private val transactionPageSize = 10

    private val _uiState = MutableStateFlow(TransferListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchBalance()
        fetchFakeTransaction()
    }

    private fun fetchBalance() {
        _uiState.update { it.copy(balance = ViewResource.Loading()) }
        viewModelScope.launch {
            when (val result = repository.getBalance()) {
                is Either.Success ->
                    _uiState.update { it.copy(balance = ViewResource.Success(result.value)) }

                is Either.Failure ->
                    _uiState.update { it.copy(balance = ViewResource.Failure(result.error)) }
            }
        }
    }

    private fun fetchFakeTransaction() {
        _uiState.update { it.copy(transactions = it.transactions.loading()) }
        viewModelScope.launch {
            when (val result = repository.getTransactions(
                page = _uiState.value.transactions.page,
                count = transactionPageSize
            )) {
                is Either.Success ->
                    _uiState.update { it.copy(transactions = it.transactions.appendData(result.value)) }

                is Either.Failure ->
                    _uiState.update { it.copy(transactions = it.transactions.error(result.error)) }
            }
        }
    }

    fun attemptLoadMoreTransactions() {
        val shouldIgnore = _uiState.value.transactions is PaginationViewResource.Loading
        Timber.d("requested to loading more transactions. result: ${if (shouldIgnore) "ignored" else "proceed"}")
        if (shouldIgnore) return

        fetchFakeTransaction()
    }

    fun attemptFetchAll() {
        fetchBalance()
        attemptLoadMoreTransactions()
    }
}