package com.noshufou.recyclerviewplayground.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.noshufou.recyclerviewplayground.R
import com.noshufou.recyclerviewplayground.common.GroupItem
import com.noshufou.recyclerviewplayground.common.PersonItem
import com.noshufou.recyclerviewplayground.common.inflate
import kotlinx.android.synthetic.main.header_item.view.*
import java.util.*

/**
 * Created by Adam on 4/22/2016.
 */

class DelegateAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), StickyHeaderAdapter<DelegateAdapter.HeaderViewHolder> {
    private var groups: ArrayList<GroupItem>
    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

    init {
        delegateAdapters.put(AdapterConstants.PERSON, PersonDelegateAdapter())
        groups = ArrayList()
        items = ArrayList()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters[viewType].onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return this.items[position].getViewType()
    }

    override fun getHeaderId(position: Int): Int {
        return (items[position] as PersonItem).group
    }

    override fun onCreateHeaderViewHolder(parent: ViewGroup): HeaderViewHolder {
        return HeaderViewHolder(parent)
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as HeaderViewHolder
        holder.bind(groups[(items[position] as PersonItem).group])
    }

    fun addPeople(people: List<PersonItem>) {
        val positionStart = items.size
        items.addAll(people)
        notifyItemRangeInserted(positionStart, people.size)
        Log.d("DelegateAdapter", "There are now ${items.size} People")
    }

    fun addGroups(groups: List<GroupItem>) {
        this.groups.addAll(groups)
    }

    class HeaderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.header_item)) {
        fun bind(item: GroupItem) = with(itemView) {
            name.text = item.name
        }
    }

}