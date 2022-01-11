package com.nyi.test.temp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.nyi.test.R
import com.nyi.test.SimpleRecyclerViewAdapter.SimpleRvViewHolder

class ListViewAdapter (context : Context) : ArrayAdapter<String>(context, R.layout.item_list_view){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemAtPosition = getItem(position)!!

        //Inflate
        var itemView : View
        if(convertView == null) {
            itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_view, parent, false)
        } else {
            itemView = convertView
        }

       //Bind
        val viewHolder = SimpleRvViewHolder(itemView)
        viewHolder.onBind(itemAtPosition)

        //Bind
        val tvText = itemView!!.findViewById<TextView>(R.id.tv_name)
        tvText.text = itemAtPosition

        return itemView
    }
}

