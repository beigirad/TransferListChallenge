package ir.beigirad.challenge.transferlist.widget

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RelativeCornerSize
import com.google.android.material.shape.ShapeAppearanceModel
import ir.beigirad.challenge.transferlist.R
import ir.beigirad.challenge.transferlist.databinding.TransferListBigActionBinding

/**
 * Created by Farhad Beigirad on 6/22/23.
 */
class BigActionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {
    private val binding = TransferListBigActionBinding.inflate(LayoutInflater.from(context), this)

    var icon: Drawable? = null
        set(value) {
            field = value

            binding.ivIcon.setImageDrawable(value)
        }

    var iconTint: Int = 0
        set(value) {
            field = value

            binding.ivIcon.imageTintList = ColorStateList.valueOf(value)
        }

    var backgroundTint: Int = 0
        set(value) {
            field = value

            (binding.iconContainer.background as MaterialShapeDrawable).fillColor = ColorStateList.valueOf(value)
        }

    var label: CharSequence? = null
        set(value) {
            field = value

            binding.tvLabel.text = value
        }

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER

        binding.iconContainer.background = MaterialShapeDrawable(
            ShapeAppearanceModel.builder()
                .setAllCornerSizes(RelativeCornerSize(0.5f))
                .build()
        )

        context.obtainStyledAttributes(attrs, R.styleable.BigActionView, defStyleAttr, 0)
            .use {
                if (it.hasValue(R.styleable.BigActionView_label))
                    label = it.getString(R.styleable.BigActionView_label)
                if (it.hasValue(R.styleable.BigActionView_icon))
                    icon = it.getDrawable(R.styleable.BigActionView_icon)
                if (it.hasValue(R.styleable.BigActionView_icon_tint))
                    iconTint = it.getColor(R.styleable.BigActionView_icon_tint, Color.WHITE)
                if (it.hasValue(R.styleable.BigActionView_background_tint))
                    backgroundTint = it.getColor(R.styleable.BigActionView_background_tint, Color.WHITE)
            }
    }
}