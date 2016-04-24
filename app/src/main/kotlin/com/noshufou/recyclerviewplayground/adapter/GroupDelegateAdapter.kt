package com.noshufou.recyclerviewplayground.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.noshufou.recyclerviewplayground.R
import com.noshufou.recyclerviewplayground.common.GroupItem
import com.noshufou.recyclerviewplayground.common.inflate
import kotlinx.android.synthetic.main.item_group.view.*

/**
 * Created by Adam on 4/23/2016.
 */

class GroupDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return GroupViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as GroupViewHolder
        holder.bind(item as GroupItem)
    }

    class GroupViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_group)
    ) {
        fun bind(item: GroupItem) = with(itemView) {
            name.text = item.name
        }
    }
}