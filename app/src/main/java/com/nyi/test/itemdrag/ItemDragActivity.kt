package com.nyi.test.itemdrag

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.selection.*
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nyi.test.databinding.ActivityItemDragBinding
import com.nyi.test.databinding.ActivityItemSelectionBinding
import java.util.*

class ItemDragActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemDragBinding

    val data2 = listOf(
        "Matrix", "Harry Potter", "Lord of the Ring", "GOT", "Big Bang", "Silicon Valley", "Start Up",
    )

    private val adapter = ItemDragListViewAdapter(onItemClick = {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    })

    private val itemTouchHelper by lazy {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(UP or DOWN or START or END, 0) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                //on move lets you check if an item has been moved from its position either up or down

                //getting the adapter
                val adapter = recyclerView.adapter as ItemDragListViewAdapter

                //the position from where item has been moved
                val from = viewHolder.adapterPosition

                //the position where the item is moved
                val to = target.adapterPosition

                //telling the adapter to move the item
                adapter.notifyItemMoved(from, to)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //on swipe tells you when an item is swiped left or right from its position ( swipe to delete)
            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)
                //when an item changes its location that is currently selected this function is called

                if (actionState == ACTION_STATE_DRAG) {
                    viewHolder?.itemView?.alpha = 0.5f
                }
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)
                //when we stop dragging , swiping or moving an item this function is called

                viewHolder.itemView.alpha = 1.0f
            }
        }
        ItemTouchHelper(simpleItemTouchCallback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemDragBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovie.layoutManager = LinearLayoutManager(this)
        binding.rvMovie.adapter = adapter
        adapter.submitList(data2)

        binding.btnReload.setOnClickListener {
            adapter.submitList(data2)
        }
        itemTouchHelper.attachToRecyclerView(binding.rvMovie)
    }
}