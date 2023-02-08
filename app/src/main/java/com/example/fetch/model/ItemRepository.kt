package com.example.fetch.model

import com.example.fetch.model.api.ItemService
import retrofit2.Call

class ItemRepository(val itemService: ItemService) {
    suspend fun fetchItems() : Call<List<Item>> {
        return itemService.fetchItems()
    }

}