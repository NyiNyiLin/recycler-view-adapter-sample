package com.nyi.test.temp

import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import com.nyi.test.R

/*
class SimpleListAdapter : ListAdapter {

    //other override implementation
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemAtPosition = getItem(position)!! as String

        //Inflate
        var itemView : View
        if(convertView == null) {
            itemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_list_view, parent, false)
        } else {
            itemView = convertView
        }

        //Bind
        val viewHolder = SimpleRvViewHolder(itemView)
        viewHolder.onBind(itemAtPosition)

        return itemView
    }

    class SimpleRvViewHolder(itemView: View) {

        private val textView = itemView.findViewById<TextView>(R.id.tv_name)

        fun onBind(dataItem : String) {
            textView.text = dataItem
        }
    }
}

 */