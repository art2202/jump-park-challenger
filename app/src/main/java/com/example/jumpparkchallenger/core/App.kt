package com.example.jumpparkchallenger.core

import android.app.Application
import com.example.jumpparkchallenger.core.utils.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(){

    private val myModule = listOf(
        utilsModule,
        databaseModule,
        dataSourceModule,
        mapperModule,
        repositoryModule,
        useCaseModule,
        viewModelModule
    )

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