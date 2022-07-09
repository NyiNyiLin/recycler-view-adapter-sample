package com.nyi.test

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nyi.test.itemselectioncustom.IntModel

class SimpleRecyclerViewAdapter : RecyclerView.Adapter<SimpleRecyclerViewAdapter.SimpleRvViewHolder>() {

    private var data : List<String> = listOf()

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SimpleRvViewHolder, position: Int) {
        Log.d("Test", "RV Change Position $position")
        holder.onBind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleRvViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_view, parent, false)

        return SimpleRvViewHolder(itemView)
    }

    /*fun addData(data : List<IntModel>) {
        this.data = data
        val oldList = this.data
        val newList = data
        val maxSize = Math.max(newList.size, oldList.size)
        for (index in 0..maxSize) {
            val newData = newList.getOrNull(index)
            val oldData = oldList.getOrNull(index)

            if (newData == null) {
                notifyItemRemoved(index)
                return
            }

            if (oldData == null) {
                notifyItemInserted(index)
                return
            }

            if (newData != oldData) {
                notifyItemChanged(index)
                return
            }
        }
    }*/

    class SimpleRvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textView = itemView.findViewById<TextView>(R.id.tv_name)

        fun onBind(dataItem : String) {
            textView.text = dataItem
        }
    }
    /*
    val oldList = this.data
        val newList = data
        val maxSize = Math.max(newList.size, oldList.size)
        for (index in 0..maxSize) {
            val newData = newList.getOrNull(index)
            val oldData = oldList.getOrNull(index)

            if (newData == null) {
                notifyItemRemoved(index)
                return
            }

            if (oldData == null) {
                notifyItemInserted(index)
                return
            }

            if (newData != oldData) {
                notifyItemChanged(index)
                return
            }
        }
     */
}