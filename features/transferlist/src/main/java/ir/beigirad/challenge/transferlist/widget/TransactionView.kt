package ir.beigirad.challenge.transferlist.widget

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RelativeCornerSize
import com.google.android.material.shape.ShapeAppearanceModel
import ir.beigirad.challenge.common.DateConvertor
import ir.beigirad.challenge.common.util.obtainThemeColor
import ir.beigirad.challenge.common.util.toPx
import ir.beigirad.challenge.model.TransactionEntity
import ir.beigirad.challenge.model.TransactionType
import ir.beigirad.challenge.transferlist.R
import ir.beigirad.challenge.transferlist.databinding.TransferListTransactionBinding

/**
 * Created by Farhad Beigirad on 6/23/23.
 */
class TransactionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding = TransferListTransactionBinding.inflate(LayoutInflater.from(context), this)

    private val activeColor =
        context.obtainThemeColor(ir.beigirad.challenge.theme.R.attr.transaction_active_icon)
    private val nonActiveColor =
        context.obtainThemeColor(ir.beigirad.challenge.theme.R.attr.transaction_non_active_icon)
    private val activePriceDrawable = ColorDrawable(activeColor).apply { alpha = 255 / 3 }

    var model: TransactionEntity? = null
        set(value) {
            field = value

            binding.tvTitle.text = value?.title
            binding.tvDescription.text =
                if (value != null)
                    DateConvertor.convert(value.date)
                        .run { "%s، %d %s %d %d:%02d".format(weekDayName, day, monthName, year, hour, minute) }
                else null

            binding.tvPrice.text =
                if (value != null)
                    context.resources.getString(
                        R.string.transfer_list_price_formatted,
                        "%,d".format(value.amount.asNumber())
                    )
                else null
            binding.tvPrice.background = when (value?.type) {
                TransactionType.DepositGift -> activePriceDrawable
                TransactionType.Remittance,
                TransactionType.Fee,
                TransactionType.Atm,
                null -> null
            }

            binding.ivIcon.setImageResource(
                when (value?.type) {
                    TransactionType.DepositGift -> R.drawable.transfer_list_transaction_deposit_gift
                    TransactionType.Remittance -> R.drawable.transfer_list_transaction_remittance
                    TransactionType.Fee -> R.drawable.transfer_list_transaction_fee
                    TransactionType.Atm -> R.drawable.transfer_list_transaction_atm
                    null -> 0
                }
            )
            binding.ivIcon.imageTintList = ColorStateList.valueOf(
                when (value?.type) {
                    TransactionType.DepositGift -> activeColor
                    TransactionType.Remittance,
                    TransactionType.Fee,
                    TransactionType.Atm -> nonActiveColor

                    null -> Color.BLACK
                }
            )
            (binding.ivIcon.background as MaterialShapeDrawable).fillColor = ColorStateList.valueOf(
                when (value?.type) {
                    TransactionType.DepositGift -> activeColor
                    TransactionType.Remittance,
                    TransactionType.Fee,
                    TransactionType.Atm -> nonActiveColor

                    null -> Color.BLACK
                }
            )
        }

    init {
        binding.ivIcon.background = MaterialShapeDrawable(
            ShapeAppearanceModel.builder()
                .setAllCornerSizes(RelativeCornerSize(0.5f))
                .build()
        ).apply { alpha = 255 / 3 }

        setPaddingRelative(16.toPx, 12.toPx, 16.toPx, 12.toPx)

        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    }
}