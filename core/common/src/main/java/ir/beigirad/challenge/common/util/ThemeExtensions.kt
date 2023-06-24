package ir.beigirad.challenge.common.util

import android.content.Context

fun Context.obtainThemeColor(attrId: Int): Int =
    theme.obtainStyledAttributes(intArrayOf(attrId))
        .use { it.getColor(0, 0) }