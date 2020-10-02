package com.example.firebasenotificationtesting

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.row_layout.view.*

class ListHistoryAdapter(internal var activity: AllHistory,
                        internal var lastHistory:List<History>): BaseAdapter() {

    internal var inflater:LayoutInflater

    init {
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    override fun getCount(): Int {
        return lastHistory.size
    }

    override fun getItem(p0: Int): Any {
        return lastHistory[p0]
    }

    override fun getItemId(p0: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val rowView:View
        rowView = inflater.inflate(R.layout.row_layout, null)

        rowView.tvTitle.text = lastHistory[p0].title.toString()
        rowView.tvMessage.text = lastHistory[p0].message.toString()


        return rowView
    }
}