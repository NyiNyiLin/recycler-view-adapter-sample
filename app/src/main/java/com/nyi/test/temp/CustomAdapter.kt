package com.nyi.test.temp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.nyi.test.R

class CustomAdapter : BaseAdapter() {

    val data = listOf("Cat", "Lion", "Dog", "Etc")

    override fun getItemId(p0: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return data[0]
    }

    override fun getView(p0: Int, convertView: View?, p2: ViewGroup?): View {
        val itemAtPosition = getItem(p0)

        //inflate
        val itemView : View

        if(convertView == null) {
            itemView = LayoutInflater.from(convertView?.context).inflate(R.layout.item_list_view, p2, false)
        } else {
            itemView = convertView
        }

        //Bind
        val viewHolder = CustomViewHolder(itemView)
        viewHolder.bind(itemAtPosition)

        return itemView
    }

    class CustomViewHolder(val itemView : View){

        fun bind(data : Any) {
            val tvText = itemView.findViewById<TextView>(R.id.tv_name)
            tvText.text = data as String
        }
    }
}