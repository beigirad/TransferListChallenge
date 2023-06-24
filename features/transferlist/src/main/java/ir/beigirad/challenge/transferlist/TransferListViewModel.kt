package ir.beigirad.challenge.transferlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.beigirad.challenge.common.Either
import ir.beigirad.challenge.common.ViewResource
import ir.beigirad.challenge.data.repository.TransferRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Farhad Beigirad on 6/23/23.
 */
@HiltViewModel
class TransferListViewModel @Inject constructor(
    private val repository: TransferRepository,
) : ViewModel() {

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
        _uiState.update { it.copy(transactions = ViewResource.Loading()) }
        viewModelScope.launch {
            when (val result = repository.getAll()) {
                is Either.Success ->
                    _uiState.update { it.copy(transactions = ViewResource.Success(result.value)) }

                is Either.Failure ->
                    _uiState.update { it.copy(transactions = ViewResource.Failure(result.error)) }
            }
        }
    }

    fun attemptFetchAll() {
        fetchBalance()
        fetchFakeTransaction()
    }
}