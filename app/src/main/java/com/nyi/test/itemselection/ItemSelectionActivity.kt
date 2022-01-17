package com.nyi.test.itemselection

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.selection.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.nyi.test.databinding.ActivityItemSelectionBinding
import java.util.*

class ItemSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemSelectionBinding

    private val adapter = ItemSelectionAdapter()
    private var tracker: SelectionTracker<Long>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvAnimal.layoutManager = LinearLayoutManager(this)
        binding.rvAnimal.adapter = adapter
        setupAdapter()
        setupTracker()

        binding.btnReload.setOnClickListener {
            setupAdapter()
        }
    }

    private fun setupAdapter() {
        adapter.list = createRandomIntList()
        adapter.notifyDataSetChanged()
    }

    /*
    @NonNull String selectionId,
                @NonNull RecyclerView recyclerView,
                @NonNull ItemKeyProvider<K> keyProvider,
                @NonNull ItemDetailsLookup<K> detailsLookup,
                @NonNull StorageStrategy<K> storage)
     */
    private fun setupTracker() {
        tracker = SelectionTracker.Builder<Long>(
            "mySelection",
            binding.rvAnimal,
            StableIdKeyProvider(binding.rvAnimal),
            MyItemDetailsLookup(binding.rvAnimal),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        tracker?.addObserver(
            object : SelectionTracker.SelectionObserver<Long>() {
                override fun onSelectionChanged() {
                    super.onSelectionChanged()
                    val nItems: Int? = tracker?.selection!!.size()
                    if (nItems == 2) {
                        launchSum(tracker?.selection!!)
                    }
                }
            })

        adapter.tracker = tracker
    }

    private fun launchSum(selection: Selection<Long>) {
        val list = selection.map {
            adapter.list[it.toInt()]
        }.toList()
        var total = 0
        list.map {
            total += it
        }
        Toast.makeText(this, total.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun createRandomIntList(): List<Int> {
        val random = Random()
        return (1..10).map { random.nextInt() }
    }
}