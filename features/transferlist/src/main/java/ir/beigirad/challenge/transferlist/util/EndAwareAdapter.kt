package ir.beigirad.challenge.transferlist.util

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Farhad Beigirad on 6/25/23.
 *
 * This class must be add as the last child of [androidx.recyclerview.widget.ConcatAdapter] to be able to work as scroll-end listener.
 *
 * Despite of being [RecyclerView.Adapter] and adapter's responsibility, This class is responsible for calling
 * the [onScrolledEnd] when recyclerView requests to bind the item.
 *
 * This is a workaround to not to avoid old {@link RecyclerView#addOnScrollListener()} with complex calculations or paging library
 *
 * @see <a href="https://gist.github.com/nesquena/d09dc68ff07e845cc622">https://gist.github.com/nesquena/d09dc68ff07e845cc622</a>
 * @see <a href="https://developer.android.com/topic/libraries/architecture/paging/v3-overview">Paging library</a>
 */
class EndAwareAdapter(
    val onScrolledEnd: () -> Unit,
    val hasMoreToLoad: () -> Boolean,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        object : RecyclerView.ViewHolder(View(parent.context)) {}

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (hasMoreToLoad()) onScrolledEnd()
    }

    override fun getItemCount(): Int = if (hasMoreToLoad()) 1 else 0
}