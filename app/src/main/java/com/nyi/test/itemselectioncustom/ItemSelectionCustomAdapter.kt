package com.nyi.test.itemselectioncustom

import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nyi.test.R
import com.nyi.test.diffCallBackWith

class ItemSelectionCustomAdapter(
    private val onItemClick: (data: IntModel) ->Unit
) : ListAdapter<IntModel, ItemSelectionCustomAdapter.ItemSelectionCustomViewHolder>(
    diffCallBackWith(
        areItemTheSame = { item1, item2 ->
            item1.value == item2.value
        },
        areContentsTheSame = { item1, item2 ->
            item1 == item2
        }
    )
) {

    override fun onBindViewHolder(holder: ItemSelectionCustomViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSelectionCustomViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list_selection, parent, false)
        return ItemSelectionCustomViewHolder(itemView, onItemClick)
    }

    inner class ItemSelectionCustomViewHolder(
        view: View,
        private val onItemClick: (data: IntModel) ->Unit
    ) : RecyclerView.ViewHolder(view) {

        private var text: TextView = view.findViewById(R.id.text)
        private lateinit var data : IntModel

        init {
            view.rootView.setOnClickListener {
                onItemClick.invoke(data)
            }
        }

        fun bind(value : IntModel) {
            this.data = value
            text.text = value.value.toString()
            itemView.isActivated = value.isSelected
        }
    }
}