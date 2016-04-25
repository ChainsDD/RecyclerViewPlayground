package com.noshufou.recyclerviewplayground.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.noshufou.recyclerviewplayground.adapter.AdapterConstants
import com.noshufou.recyclerviewplayground.common.getViewType

/**
 * Created by Adam on 4/23/2016.
 */

class PinnedHeaderDecoration(context: Context, type: Int) : RecyclerView.ItemDecoration() {
    val type = type
    var holder: RecyclerView.ViewHolder? = null
    val rect = Rect()

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        // If we don't have a view holder yet, create it, and set up
        // the rect
        if (holder == null) {
            holder = parent.adapter.createViewHolder(parent, type)
            val child = parent.getChildAt(0)
            rect.set(child.left, child.top, child.right, child.bottom)
        }

        var doDraw = true
        var top = 0
        var firstChild = parent.getChildAt(0)
        if (parent.getViewType(firstChild) == AdapterConstants.GROUP) {
            if (firstChild.top > 0) {
                doDraw = false
            }
        } else {
            var firstGroup: View? = null
            for (i in 1..parent.childCount - 1) {
                firstGroup = parent.getChildAt(i)
                if (parent.getViewType(firstGroup) == AdapterConstants.GROUP) break
            }
            if (firstGroup != null) {
                if (firstGroup.top < rect.height()) {
                    top = firstGroup.top - rect.height()
                }
            }
        }
        parent.adapter.bindViewHolder(holder, parent.getChildAdapterPosition(firstChild))
        if (doDraw) {
            val itemView = holder!!.itemView
            itemView.layout(0, 0, rect.width(), rect.height())
            c.save()
            c.translate(rect.left.toFloat(), top.toFloat())
            itemView.draw(c)
            c.restore()
        }
    }
}
