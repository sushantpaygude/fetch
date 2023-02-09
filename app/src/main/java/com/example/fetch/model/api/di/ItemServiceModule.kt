package com.example.fetch.model.api.di

import com.example.fetch.model.api.ItemService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ItemServiceModule {
        @Provides
        fun provideItemService() : ItemService {
            val retrofit = Retrofit.Builder()
                .baseUrl(ItemService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ItemService::class.java)
        }
}