package com.nyi.test.itemselectioncustom

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.selection.*
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nyi.test.databinding.ActivityItemSelectionBinding
import java.util.*

class ItemSelectionCustomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemSelectionBinding

    private var dataList = createRandomIntList()

    private val adapter = ItemSelectionCustomAdapter(onItemClick = {
        onClickItem(it)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvAnimal.layoutManager = LinearLayoutManager(this)
        binding.rvAnimal.adapter = adapter
        adapter.submitList(dataList)

        binding.btnReload.setOnClickListener {
            dataList = createRandomIntList()
            adapter.submitList(dataList)
        }
    }

    private fun createRandomIntList(): List<IntModel> {
        val random = Random()
        return (1..10).map { IntModel(random.nextInt(), false) }
    }

    private fun onClickItem(clickedItem : IntModel) {
        dataList = dataList.map {
            if(it.value == clickedItem.value) it.copy(isSelected = clickedItem.isSelected.not())
            else it
        }
        adapter.submitList(dataList)
        Log.e("Test", "Test $dataList")
    }
}