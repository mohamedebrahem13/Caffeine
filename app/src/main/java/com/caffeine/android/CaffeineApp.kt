package com.caffeine.android

import android.app.Application
import com.caffeine.di.coffeeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CaffeineApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CaffeineApp)
            modules(listOf(coffeeModule))
        }
    }
}