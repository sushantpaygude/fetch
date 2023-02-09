package com.example.fetch.model.api

import com.example.fetch.model.Item
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ItemService {

    @GET("hiring.json")
    fun fetchItems() : Call<List<Item>>

    companion object {
        val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"
    }
}

