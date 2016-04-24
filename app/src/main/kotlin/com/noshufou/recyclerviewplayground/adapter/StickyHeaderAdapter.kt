package com.noshufou.recyclerviewplayground.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by Adam on 4/22/2016.
 */

interface StickyHeaderAdapter<T : RecyclerView.ViewHolder> {
    fun getHeaderId(position: Int): Int
    fun onCreateHeaderViewHolder(parent: ViewGroup): T
    fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder, position: Int)
}