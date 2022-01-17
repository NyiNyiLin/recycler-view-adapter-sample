package com.nyi.test.animation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nyi.test.R
import com.nyi.test.diffCallBackWith

class AnimationListViewAdapter(
    private val onItemClick: (data: String) ->Unit
) : ListAdapter<String, AnimationListViewAdapter.ItemDragListViewHolder>(
    diffCallBackWith(
        areItemTheSame = { item1, item2 ->
            item1 == item2
        },
        areContentsTheSame = { item1, item2 ->
            item1 == item2
        }
    )
) {

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun onBindViewHolder(holder: ItemDragListViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDragListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_view, parent, false)

        return ItemDragListViewHolder(itemView, onItemClick)
    }

    class ItemDragListViewHolder(
        itemView: View,
        private val onTextViewClicked: (position: String) ->Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val textView = itemView.findViewById<TextView>(R.id.tv_name)

        private lateinit var dataItem : String

        init {
            textView.setOnClickListener {
                onTextViewClicked.invoke(dataItem)
            }
        }

        fun onBind(dataItem : String) {
            this.dataItem = dataItem
            textView.text = dataItem
        }
    }
}