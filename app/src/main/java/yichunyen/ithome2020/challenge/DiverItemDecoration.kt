package yichunyen.ithome2020.challenge

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DiverItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val padding = view.resources.getDimensionPixelSize(R.dimen.default_padding)
        when (parent.getChildAdapterPosition(view)) {
            0 -> {
                outRect.top = padding
            }
            state.itemCount - 1 -> {
                outRect.bottom = padding
            }
            else -> {
                super.getItemOffsets(outRect, view, parent, state)
            }
        }
    }
}