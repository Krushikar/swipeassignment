package com.example.assignmentswipebalusonawane

import android.app.Application
import com.example.assignmentswipebalusonawane.di.module.appModule
import com.example.assignmentswipebalusonawane.di.module.repoModule
import com.example.assignmentswipebalusonawane.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        //Init koin for dependency
        startKoin {
            androidContext(applicationContext)
            modules(appModule, repoModule, viewModelModule)
        }
    }
}