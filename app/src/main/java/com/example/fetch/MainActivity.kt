package com.example.fetch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetch.databinding.ActivityMainBinding
import com.example.fetch.di.ItemApplication
import com.example.fetch.view.GroupAdapter
import com.example.fetch.viewmodel.ItemViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var itemViewModel: ItemViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as ItemApplication).applicationComponent.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        fetchItems()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter()
        itemViewModel.itemList.observe(this) {
            groupAdapter.itemList = it
            groupAdapter.notifyDataSetChanged()
        }
        binding.recyclerParent.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }

    private fun fetchItems() {
        CoroutineScope(Dispatchers.IO).launch {
            itemViewModel.fetchItems()
        }
    }
}