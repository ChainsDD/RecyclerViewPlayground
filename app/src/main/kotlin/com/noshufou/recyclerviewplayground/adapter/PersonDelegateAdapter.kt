package com.noshufou.recyclerviewplayground.adapter

import android.support.v7.widget.RecyclerView
import android.telephony.PhoneNumberUtils
import android.view.ViewGroup
import com.noshufou.recyclerviewplayground.R
import com.noshufou.recyclerviewplayground.common.PersonItem
import com.noshufou.recyclerviewplayground.common.inflate
import kotlinx.android.synthetic.main.item_person.view.*

/**
 * Created by Adam on 4/22/2016.
 */

class PersonDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return PersonViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as PersonViewHolder
        holder.bind(item as PersonItem)
    }

    class PersonViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_person)) {
        fun bind(item: PersonItem) = with(itemView) {
            name.text = item.name
            number.text = PhoneNumberUtils.formatNumber(item.number, "US")
            group.text = item.group.toString()
        }
    }
}