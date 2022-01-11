package com.nyi.test.temp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nyi.test.R
import com.nyi.test.model.Movie

class TestRecyclerViewAdapter : RecyclerView.Adapter<TestRecyclerViewAdapter.TestRvViewHolder>() {

    private var data : List<Movie> = listOf()

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return data[position].id.toLong()
    }

    override fun onBindViewHolder(holder: TestRvViewHolder, position: Int) {
        Log.d("Test", "Test Change Position $position")
        holder.onBind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestRvViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_view, parent, false)

        return TestRvViewHolder(itemView)
    }

    fun addData(data : List<Movie>) {
        this.data = data
        notifyDataSetChanged()
    }

    class TestRvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textView = itemView.findViewById<TextView>(R.id.tv_name)

        fun onBind(dataItem : Movie) {
            textView.text = dataItem.name
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