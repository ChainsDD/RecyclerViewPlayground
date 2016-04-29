package com.noshufou.recyclerviewplayground.common

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Adam on 4/22/2016.
 */

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false)
        = LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

val ViewGroup.views: List<View> get() = (0 until childCount).map { getChildAt(it) }

operator fun ViewGroup.get(pos: Int): View = getChildAt(pos)

fun RecyclerView.getViewType(view: View) = adapter.getItemViewType(getChildAdapterPosition(view))

val Any.TAG: String get() = this.javaClass.simpleName