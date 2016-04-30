package com.noshufou.recyclerviewplayground.ui

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.noshufou.recyclerviewplayground.common.TAG

/**
 * Created by Adam on 4/29/2016.
 */

class PinnedHeaderLayoutManager(ctx: Context, type: Int) : LinearLayoutManager(ctx) {
    var firstPosition = 0
    val pinnedHeaderType = type
    var pinnedHeaderPosition = 0

    override fun generateDefaultLayoutParams() =
            RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

    override fun canScrollVertically() = true

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        Log.d(TAG, "onLayoutChildren()")
        val parentBottom = height - paddingBottom
        val oldTop = getChildAt(0)?.top ?: paddingTop

        detachAndScrapAttachedViews(recycler)

        var top = oldTop
        var bottom: Int
        val left = paddingLeft
        val right = width - paddingRight

        val count = state.itemCount
        var i = 0
        while (firstPosition + i < count && top < parentBottom) {
            val v = recycler.getViewForPosition(firstPosition + i)
            addView(v, i)
            measureChildWithMargins(v, 0, 0)
            bottom = top + getDecoratedMeasuredHeight(v)
            layoutDecorated(v, left, top, right, bottom)
            i++
            top = bottom
        }
    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        Log.d(TAG, "scrollVerticalBy()")
        return super.scrollVerticallyBy(dy, recycler, state)
    }

}