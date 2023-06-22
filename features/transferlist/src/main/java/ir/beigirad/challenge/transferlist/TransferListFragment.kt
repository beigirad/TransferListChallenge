package ir.beigirad.challenge.transferlist

import android.os.Bundle
import android.view.View
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
    }
}