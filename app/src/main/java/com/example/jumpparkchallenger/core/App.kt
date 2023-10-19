package com.example.jumpparkchallenger.core

import android.app.Application
import com.example.jumpparkchallenger.core.utils.databaseModule
import com.example.jumpparkchallenger.core.utils.modules
import com.example.jumpparkchallenger.core.utils.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(){

    private val myModule = listOf(modules, viewModelModule, databaseModule)

    companion object{
        lateinit var instance : App
    }

    init{
        instance = this
    }
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@App)
            modules(myModule)
        }
    }

}