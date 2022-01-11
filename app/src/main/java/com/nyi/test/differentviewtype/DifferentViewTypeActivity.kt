package com.nyi.test.differentviewtype

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.selection.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.nyi.test.databinding.ActivityDifferentViewtypeBinding
import com.nyi.test.databinding.ActivityItemSelectionBinding
import java.util.*

class DifferentViewTypeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDifferentViewtypeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDifferentViewtypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = listOf<MovieInterface>(
            MovieType(1, "Adventure"),
            MovieDetail(1, "Spider Man"),
            MovieDetail(2, "Bad Man"),
            MovieDetail(3, "Iron Man"),
            MovieType(2, "Comedy"),
            MovieDetail(4, "Ha Ha"),
            MovieDetail(5, "Hee Hee"),
            MovieDetail(6, "Hoo Hoo"),
        )
        val adapter = DifferentViewTypeAdapter(onItemClick = {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        })

        binding.rvMovie.layoutManager = LinearLayoutManager(this)
        binding.rvMovie.adapter = adapter
        adapter.submitList(data)

        binding.btnReload.setOnClickListener {
            adapter.submitList(data)
        }
    }
}