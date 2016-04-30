package com.noshufou.recyclerviewplayground.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.noshufou.recyclerviewplayground.adapter.AdapterConstants
import com.noshufou.recyclerviewplayground.common.TAG
import com.noshufou.recyclerviewplayground.common.get
import com.noshufou.recyclerviewplayground.common.getViewType
import com.noshufou.recyclerviewplayground.common.views

/**
 * Created by Adam on 4/23/2016.
 */

class PinnedHeaderDecoration(context: Context, type: Int) : RecyclerView.ItemDecoration() {
    val type = type
    var holder: RecyclerView.ViewHolder? = null
//    val rect = Rect()

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        Log.d(TAG, "getItemOffsets")
        if (parent.getViewType(view) == type && holder == null) {
            holder = parent.adapter.createViewHolder(parent, type)
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        Log.d(TAG, "onDrawOver")
        // If there aren't any children in the RecyclerView, don't even bother
        if (state.itemCount == 0) return

        var top = 0
        val child = parent.views.firstOrNull { parent.getViewType(it) == AdapterConstants.GROUP }
        val rect = Rect()
        if (child != null) {
            rect.set(child.left, child.top, child.right, child.bottom)
            if (child.top < 0) {
                top = 0
            } else if (child.top < rect.height()) {
                top = child.top - rect.height()
            }
        }
        parent.adapter.bindViewHolder(holder, parent.getChildAdapterPosition(parent[0]))
        val itemView = holder!!.itemView
        itemView.layout(0, 0, rect.width(), rect.height())
        c.save()
        c.translate(rect.left.toFloat(), top.toFloat())
        itemView.draw(c)
        c.restore()
    }
}
