package com.noshufou.recyclerviewplayground.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.noshufou.recyclerviewplayground.R

/**
 * Created by Adam on 4/23/2016.
 */

class DividerDecoration(context: Context) : RecyclerView.ItemDecoration() {
    val divider = ContextCompat.getDrawable(context, R.drawable.line_divider)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val adapter = parent.adapter
        val position = parent.getChildAdapterPosition(view)
        if (position != adapter.itemCount - 1) {
            outRect.bottom = divider.intrinsicHeight
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val adapter = parent.adapter

        for (i in 0..parent.childCount - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + divider.intrinsicHeight
            divider.bounds = Rect(left, top, right, bottom)
            divider.draw(c)
        }
    }
}