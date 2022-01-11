package com.nyi.test

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.selection.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nyi.test.databinding.ActivityDifferentViewtypeBinding
import com.nyi.test.databinding.ActivityItemSelectionBinding
import com.nyi.test.databinding.ActivitySimpleRvBinding
import com.nyi.test.model.Movie
import java.util.*

class SimpleRvActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySimpleRvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySimpleRvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = listOf(
            "Matrix", "Harry Potter", "Lord of the Ring"
        )

        val data2 = listOf(
            "Matrix", "Harry Potter", "Lord of the Ring", "GOT", "Big Bang", "Silicon Valley", "Start Up",
        )

        // recycler adapter
        val simpleRecyclerAdapter = SimpleRecyclerViewAdapter()

        binding.rvAnimal.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvAnimal.adapter = simpleRecyclerAdapter

        simpleRecyclerAdapter.addData(data)

        binding.btnReload.setOnClickListener {
            simpleRecyclerAdapter.addData(data)
        }


/*
        // list adapter
        val listAdapter = SimpleListViewAdapter(onItemClick = { data ->
            Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
        })

        binding.rvAnimal.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvAnimal.adapter = listAdapter

        listAdapter.submitList(data)

        binding.btnReload.setOnClickListener {
            listAdapter.submitList(data)
        }*/
    }
}