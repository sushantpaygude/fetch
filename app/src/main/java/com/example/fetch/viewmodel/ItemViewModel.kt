package com.example.fetch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fetch.model.*
import com.example.fetch.model.api.ItemService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class ItemViewModel : ViewModel() {
    val itemList = MutableLiveData<List<ListItem>>()

    private val itemRepository = ItemRepository(ItemService.create())

    suspend fun fetchItems() {
       val response = itemRepository.fetchItems()
        val callback = object : retrofit2.Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                val items = response.body()
                CoroutineScope(Dispatchers.Default).launch {
                    itemList.postValue(groupItems(items))
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        }
        response.enqueue(callback)
    }

    suspend fun groupItems(itemList: List<Item>?) : List<ListItem> {
        val map = itemList?.sortedBy { it.listId }
            ?.groupBy { item -> item.listId }
        val list = mutableListOf<ListItem>()
        map?.forEach { (key,value) ->
            val header = HeaderItem(listId = key)
            list.add(header)
            value.filter { it.name != null && it.name.isNotEmpty() }
                .sortedBy { it.name }
                .forEach {
                    val content = ContentItem(it.id, it.name)
                    list.add(content)
            }
        }
        return list
    }
}