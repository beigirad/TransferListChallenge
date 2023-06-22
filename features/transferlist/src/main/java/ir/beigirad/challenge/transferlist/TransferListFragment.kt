package ir.beigirad.challenge.transferlist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.wada811.viewbinding.viewBinding
import ir.beigirad.challenge.transferlist.databinding.TransferListLayoutBinding

/**
 * Created by Farhad Beigirad on 6/22/23.
 */
class TransferListFragment : Fragment(R.layout.transfer_list_layout) {

    private val binding by viewBinding(TransferListLayoutBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.header.btnSearch.setOnClickListener { showNotImplementedToast() }
        binding.header.btnDownload.setOnClickListener { showNotImplementedToast() }
        binding.header.btnNotification.setOnClickListener { showNotImplementedToast() }
        binding.header.btnSupport.setOnClickListener { showNotImplementedToast() }
        binding.header.btnCharge.setOnClickListener { showNotImplementedToast() }
        binding.header.btnChart.setOnClickListener { showNotImplementedToast() }
        binding.header.btnSpaces.setOnClickListener { showNotImplementedToast() }

        binding.header.tvBalance.text = getString(
            R.string.transfer_list_balance_formatted,
            "%,d".format(78500000)
        )
    }

    private fun showNotImplementedToast() {
        Toast.makeText(context, "NotImplemented", Toast.LENGTH_SHORT).show()
    }
}