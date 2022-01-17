package com.nyi.test.animation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nyi.test.databinding.ActivityAnimationBinding
import com.nyi.test.databinding.ActivityItemDragBinding
import com.nyi.test.itemdrag.ItemDragListViewAdapter

class AnimationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationBinding

    val data2 = listOf(
        "Matrix", "Harry Potter", "Lord of the Ring", "GOT", "Big Bang", "Silicon Valley", "Start Up",
    )

    private val adapter = AnimationListViewAdapter(onItemClick = {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovie.layoutManager = LinearLayoutManager(this)
        binding.rvMovie.adapter = adapter
        binding.rvMovie.itemAnimator = TestItemAnimator()

        binding.btnReload.setOnClickListener {
            adapter.submitList(data2)
        }

        binding.btnClear.setOnClickListener {
            adapter.submitList(null)
            adapter.notifyDataSetChanged()
        }
    }
}