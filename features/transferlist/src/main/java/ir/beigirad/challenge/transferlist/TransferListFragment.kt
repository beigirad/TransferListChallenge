package ir.beigirad.challenge.transferlist

import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.wada811.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ir.beigirad.challenge.common.PaginationViewResource
import ir.beigirad.challenge.common.asString
import ir.beigirad.challenge.common.util.toPx
import ir.beigirad.challenge.transferlist.databinding.TransferListLayoutBinding
import ir.beigirad.challenge.transferlist.util.EndAwareAdapter
import kotlinx.coroutines.launch

/**
 * Created by Farhad Beigirad on 6/22/23.
 */
@AndroidEntryPoint
class TransferListFragment : Fragment(R.layout.transfer_list_layout) {

    private val binding by viewBinding(TransferListLayoutBinding::bind)
    private lateinit var transactionAdapter: TransactionAdapter

    private val viewModel: TransferListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.header.btnSearch.setOnClickListener { showNotImplementedToast() }
        binding.header.btnDownload.setOnClickListener { showNotImplementedToast() }
        binding.header.btnNotification.setOnClickListener { showNotImplementedToast() }
        binding.header.btnSupport.setOnClickListener { showNotImplementedToast() }
        binding.header.btnCharge.setOnClickListener { showNotImplementedToast() }
        binding.header.btnChart.setOnClickListener { showNotImplementedToast() }
        binding.header.btnSpaces.setOnClickListener { showNotImplementedToast() }

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = ConcatAdapter(
            TransactionAdapter().also { transactionAdapter = it },
            EndAwareAdapter(
                onScrolledEnd = viewModel::attemptLoadMoreTransactions,
                hasMoreToLoad = viewModel.uiState.value.transactions::hasMore
            )
        )

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.header.btnSearch.updateLayoutParams<MarginLayoutParams> { topMargin = insets.top }
            binding.sheetContentContainer.updateLayoutParams<MarginLayoutParams> {
                topMargin = insets.top
                bottomMargin = insets.bottom
            }
            WindowInsetsCompat.CONSUMED
        }

        // postpone setting bottom-sheet's peak-height after measurement of header
        binding.header.root.viewTreeObserver.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val overlapSize = 16.toPx
                    val topInset = ViewCompat.getRootWindowInsets(binding.root)
                        ?.getInsets(WindowInsetsCompat.Type.systemBars())
                        ?.top ?: 0

                    BottomSheetBehavior.from(binding.sheetContainer).peekHeight =
                        binding.root.height - binding.header.root.height + overlapSize + topInset

                    binding.header.root.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            }
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    uiState.balance.asOk()?.data?.let { balance ->
                        binding.header.tvBalance.text =
                            getString(R.string.transfer_list_price_formatted, "%,d".format(balance.asNumber()))
                    }

                    binding.errorContainer.isVisible = uiState.transactions is PaginationViewResource.Failure
                    binding.shimmer.isVisible = uiState.transactions is PaginationViewResource.Loading
                    binding.recycler.isVisible = uiState.transactions is PaginationViewResource.Success
                    when (val transactions = uiState.transactions) {
                        is PaginationViewResource.NotAvailable -> Unit // do nothing
                        is PaginationViewResource.Loading -> Unit // handled above

                        is PaginationViewResource.Failure -> {
                            binding.tvError.text = transactions.error.asString()
                            binding.btnError.setOnClickListener { viewModel.attemptFetchAll() }
                        }

                        is PaginationViewResource.Success ->
                            transactionAdapter.submitList(transactions.data)
                    }
                }
            }
        }
    }

    private fun showNotImplementedToast() {
        Toast.makeText(context, "NotImplemented", Toast.LENGTH_SHORT).show()
    }
}