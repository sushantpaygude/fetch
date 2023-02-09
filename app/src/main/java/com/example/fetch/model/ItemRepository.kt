package com.example.fetch.model

import com.example.fetch.model.api.ItemService
import retrofit2.Call
import javax.inject.Inject

class ItemRepository @Inject constructor(private val itemService: ItemService) {
    suspend fun fetchItems() : Call<List<Item>> {
        return itemService.fetchItems()
    }

}