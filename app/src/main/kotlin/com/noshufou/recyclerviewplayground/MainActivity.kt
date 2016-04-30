package com.noshufou.recyclerviewplayground

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.noshufou.recyclerviewplayground.adapter.AdapterConstants
import com.noshufou.recyclerviewplayground.adapter.DelegateAdapter
import com.noshufou.recyclerviewplayground.common.GroupItem
import com.noshufou.recyclerviewplayground.common.PersonItem
import com.noshufou.recyclerviewplayground.common.TAG
import com.noshufou.recyclerviewplayground.ui.DividerDecoration
import com.noshufou.recyclerviewplayground.ui.PinnedHeaderDecoration
import com.noshufou.recyclerviewplayground.ui.PinnedHeaderLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show() }
        recycler.layoutManager = PinnedHeaderLayoutManager(this, AdapterConstants.GROUP)
//        recycler.layoutManager = LinearLayoutManager(this)
//        Log.d(TAG, "Set LayoutManager")
//        recycler.addItemDecoration(PinnedHeaderDecoration(this, AdapterConstants.GROUP))
//        Log.d(TAG, "Add PinnedHeaderDecoration")
        recycler.addItemDecoration(DividerDecoration(this, AdapterConstants.PERSON))
        Log.d(TAG, "Add DividerDecoration")

        initAdapter()
        Log.d(TAG, "Set Adapter")

        if (savedInstanceState == null) {
            val groups = mutableListOf<GroupItem>()
            for (i in 1..10) {
                groups.add(GroupItem(i, "Group $i"))
            }
            val people = mutableListOf<PersonItem>()
            val gen = Random()
            for (i in 0..1000 step 10) {
                val person = PersonItem(
                        "Person $i",
                        "%d%d%04d".format(gen.nextInt(800) + 200, gen.nextInt(800) + 200, gen.nextInt(1000)),
                        gen.nextInt(10) + 1
                )
                people.add(person)
            }
            (recycler.adapter as DelegateAdapter).addGroups(groups)
            Log.d(TAG, "Add groups")
            (recycler.adapter as DelegateAdapter).addPeople(people)
            Log.d(TAG, "Add people")
        }
    }

    private fun initAdapter() {
        if (recycler.adapter == null) {
            recycler.adapter = DelegateAdapter()
        }
    }
}