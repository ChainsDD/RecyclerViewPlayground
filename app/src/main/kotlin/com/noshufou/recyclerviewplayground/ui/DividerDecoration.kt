package com.noshufou.recyclerviewplayground.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.noshufou.recyclerviewplayground.R
import com.noshufou.recyclerviewplayground.adapter.AdapterConstants

/**
 * Created by Adam on 4/23/2016.
 */

class DividerDecoration(context: Context, type: Int) : RecyclerView.ItemDecoration() {
    val divider = ContextCompat.getDrawable(context, R.drawable.line_divider)
    val type = type

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (position != state.itemCount - 1) {
            outRect.bottom = divider.intrinsicHeight
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (i in 0..parent.childCount - 1) {
            val child = parent.getChildAt(i)
            val adapter = parent.adapter
            val adapterPos = parent.getChildAdapterPosition(child)
            if (adapterPos == state.itemCount - 1 ||
                    adapter.getItemViewType(adapterPos) != type ||
                    adapter.getItemViewType(adapterPos + 1) != type) continue
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + divider.intrinsicHeight
            divider.bounds = Rect(left, top, right, bottom)
            divider.draw(c)
        }
    }
}