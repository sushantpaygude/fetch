package com.example.fetch.di

import android.app.Application
import com.example.fetch.MainActivity
import com.example.fetch.model.api.di.ItemServiceModule
import dagger.Component

@Component(modules = [ItemServiceModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}

class ItemApplication : Application() {
    val applicationComponent: ApplicationComponent = DaggerApplicationComponent.create()
}