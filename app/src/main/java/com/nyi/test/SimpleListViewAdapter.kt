package com.nyi.test

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nyi.test.model.Movie

class SimpleListViewAdapter(
    private val onItemClick: (data: String) ->Unit
) : ListAdapter<String, SimpleListViewAdapter.SimpleListViewHolder>(
    diffCallBackWith(
        areItemTheSame = { item1, item2 ->
            item1 == item2
        },
        areContentsTheSame = { item1, item2 ->
            item1 == item2
        }
    )
) {

    private val onTextViewTextClicked = { position: Int ->
        onItemClick.invoke(getItem(position))
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun onBindViewHolder(holder: SimpleListViewHolder, position: Int) {
        Log.d("Test", "LV Change Position $position")
        holder.onBind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_view, parent, false)

        return SimpleListViewHolder(itemView, onTextViewTextClicked)
    }

    class SimpleListViewHolder(
        itemView: View,
        private val onTextViewClicked: (position: Int) ->Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val textView = itemView.findViewById<TextView>(R.id.tv_name)

        init {
            textView.setOnClickListener {
                onTextViewClicked.invoke(adapterPosition)
            }
        }

        fun onBind(dataItem : String) {
            textView.text = dataItem
        }
    }

    override fun onViewRecycled(holder: SimpleListViewHolder) {
        super.onViewRecycled(holder)
    }
}

inline fun <T> diffCallBackWith(
    crossinline areItemTheSame: ((@ParameterName("item1") T, @ParameterName("item2") T) -> Boolean),
    crossinline areContentsTheSame: ((@ParameterName("item1") T, @ParameterName("item2") T) -> Boolean)
): DiffUtil.ItemCallback<T> {
    return object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return areItemTheSame.invoke(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return areContentsTheSame.invoke(oldItem, newItem)
        }
    }
}