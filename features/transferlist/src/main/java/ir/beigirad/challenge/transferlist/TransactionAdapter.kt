package ir.beigirad.challenge.transferlist

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.beigirad.challenge.model.TransactionEntity
import ir.beigirad.challenge.transferlist.widget.TransactionView

/**
 * Created by Farhad Beigirad on 6/23/23.
 */
class TransactionAdapter : ListAdapter<TransactionEntity, TransactionAdapter.VH>(itemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(TransactionView(parent.context))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.view.model = getItem(position)
    }

    class VH(val view: TransactionView) : RecyclerView.ViewHolder(view)

    companion object {
        private val itemCallback = object : DiffUtil.ItemCallback<TransactionEntity>() {
            override fun areItemsTheSame(oldItem: TransactionEntity, newItem: TransactionEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TransactionEntity, newItem: TransactionEntity): Boolean =
                oldItem == newItem
        }
    }
}