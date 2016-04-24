package com.noshufou.recyclerviewplayground.common

import com.noshufou.recyclerviewplayground.adapter.AdapterConstants
import com.noshufou.recyclerviewplayground.adapter.ViewType

/**
 * Created by Adam on 4/22/2016.
 */

data class PersonItem(
        val name: String,
        val number: String,
        val group: Int
) : ViewType {
    override fun getViewType() = AdapterConstants.PERSON
}

data class GroupItem(
        val id: Int,
        val name: String
) : ViewType {
    override fun getViewType() = AdapterConstants.GROUP
}