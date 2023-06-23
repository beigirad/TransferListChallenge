package ir.beigirad.challenge.common.util

import android.content.res.Resources

/**
 * Created by Farhad Beigirad on 6/23/23.
 */
val Int.toPx get() = (this * Resources.getSystem().displayMetrics.density).toInt()