package ir.beigirad.challenge.transferlist

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RelativeCornerSize
import com.google.android.material.shape.ShapeAppearanceModel
import ir.beigirad.challenge.common.DateConvertor
import ir.beigirad.challenge.common.util.obtainThemeColor
import ir.beigirad.challenge.model.TransactionEntity
import ir.beigirad.challenge.model.TransactionType
import ir.beigirad.challenge.transferlist.databinding.TransferListTransactionBinding

/**
 * Created by Farhad Beigirad on 6/23/23.
 */
class TransactionAdapter : ListAdapter<TransactionEntity, TransactionAdapter.VH>(itemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemBinding = TransferListTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(itemBinding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    class VH(private val binding: TransferListTransactionBinding) : RecyclerView.ViewHolder(binding.root) {
        private val activeColor =
            itemView.context.obtainThemeColor(ir.beigirad.challenge.theme.R.attr.transaction_active_icon)
        private val nonActiveColor =
            itemView.context.obtainThemeColor(ir.beigirad.challenge.theme.R.attr.transaction_non_active_icon)
        private val activePriceDrawable = ColorDrawable(activeColor).apply { alpha = 255 / 3 }

        init {
            binding.ivIcon.background = MaterialShapeDrawable(
                ShapeAppearanceModel.builder()
                    .setAllCornerSizes(RelativeCornerSize(0.5f))
                    .build()
            ).apply { alpha = 255 / 3 }
        }

        fun bind(model: TransactionEntity) {
            with(binding) {
                tvTitle.text = model.title
                tvDescription.text = DateConvertor.convert(model.date).run {
                    "%s، %d %s %d %d:%02d".format(weekDayName, day, monthName, year, hour, minute)
                }

                tvPrice.text = itemView.context.resources.getString(
                    R.string.transfer_list_price_formatted,
                    "%,d".format(model.amount.asNumber())
                )
                tvPrice.background = when (model.type) {
                    TransactionType.DepositGift -> activePriceDrawable
                    TransactionType.Remittance,
                    TransactionType.Fee,
                    TransactionType.Atm -> null
                }

                ivIcon.setImageResource(
                    when (model.type) {
                        TransactionType.DepositGift -> R.drawable.transfer_list_transaction_deposit_gift
                        TransactionType.Remittance -> R.drawable.transfer_list_transaction_remittance
                        TransactionType.Fee -> R.drawable.transfer_list_transaction_fee
                        TransactionType.Atm -> R.drawable.transfer_list_transaction_atm
                    }
                )
                ivIcon.imageTintList = ColorStateList.valueOf(
                    when (model.type) {
                        TransactionType.DepositGift -> activeColor
                        TransactionType.Remittance,
                        TransactionType.Fee,
                        TransactionType.Atm -> nonActiveColor
                    }
                )
                (ivIcon.background as MaterialShapeDrawable).fillColor = ColorStateList.valueOf(
                    when (model.type) {
                        TransactionType.DepositGift -> activeColor
                        TransactionType.Remittance,
                        TransactionType.Fee,
                        TransactionType.Atm -> nonActiveColor

                    }
                )
            }
        }
    }

    companion object {
        private val itemCallback = object : DiffUtil.ItemCallback<TransactionEntity>() {
            override fun areItemsTheSame(oldItem: TransactionEntity, newItem: TransactionEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TransactionEntity, newItem: TransactionEntity): Boolean =
                oldItem == newItem
        }
    }
}