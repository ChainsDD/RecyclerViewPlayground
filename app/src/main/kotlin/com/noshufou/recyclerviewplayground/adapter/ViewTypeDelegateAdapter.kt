package com.noshufou.recyclerviewplayground.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by Adam on 4/22/2016.
 */

interface ViewTypeDelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}