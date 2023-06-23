package ir.beigirad.challenge.transferlist

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.wada811.viewbinding.viewBinding
import ir.beigirad.challenge.common.Price
import ir.beigirad.challenge.common.util.toPx
import ir.beigirad.challenge.model.TransactionEntity
import ir.beigirad.challenge.model.TransactionType
import ir.beigirad.challenge.transferlist.databinding.TransferListLayoutBinding
import java.util.Calendar
import kotlin.random.Random

/**
 * Created by Farhad Beigirad on 6/22/23.
 */
class TransferListFragment : Fragment(R.layout.transfer_list_layout) {

    private val binding by viewBinding(TransferListLayoutBinding::bind)
    private lateinit var transactionAdapter: TransactionAdapter

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
        binding.recycler.adapter = TransactionAdapter().also { transactionAdapter = it }
        transactionAdapter.submitList(getFakeTransactions())

        // postpone setting bottom-sheet's peak-height after measurement of header
        binding.header.root.viewTreeObserver.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    BottomSheetBehavior.from(binding.sheetContainer).peekHeight =
                        binding.root.height - binding.header.root.height + /* overlap = */16.toPx
                    binding.header.root.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            }
        )

        binding.header.tvBalance.text = getString(
            R.string.transfer_list_price_formatted,
            "%,d".format(78500000)
        )
    }

    private fun showNotImplementedToast() {
        Toast.makeText(context, "NotImplemented", Toast.LENGTH_SHORT).show()
    }

    // FIXME provide transactions data from viewmodel
    private fun getFakeTransactions(): List<TransactionEntity> {
        val transactionTypeTitle = listOf(
            TransactionType.Remittance to "انتقال به سپرده",
            TransactionType.Remittance to "انتقال پل",
            TransactionType.Atm to "برداشت از کارت",
            TransactionType.DepositGift to "هدیه دعوت از دوستان",
            TransactionType.Fee to "کارمزد انتقال",
        )

        val calendar = Calendar.getInstance()

        return List(100) {
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
    }
}