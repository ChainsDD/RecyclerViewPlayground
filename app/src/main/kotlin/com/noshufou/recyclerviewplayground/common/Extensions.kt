package com.noshufou.recyclerviewplayground.common

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Adam on 4/22/2016.
 */

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false)
        = LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun RecyclerView.getViewType(view: View) = adapter.getItemViewType(getChildAdapterPosition(view))
