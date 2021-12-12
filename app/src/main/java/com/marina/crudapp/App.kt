package com.marina.crudapp

import android.app.Application
import com.marina.crudapp.modules.globalViewModel
import com.marina.crudapp.modules.insertModifyUserViewModel
import com.marina.crudapp.modules.mainViewModel
import com.marina.data.modules.networkModule
import com.marina.data.modules.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            val modulesList = mutableListOf(
                networkModule,
                repositoryModule,
                globalViewModel,
                mainViewModel,
                insertModifyUserViewModel
            )
            modules(modulesList)
        }
    }

}