package com.marina.crudapp.modules

import com.marina.crudapp.App
import com.marina.crudapp.usecases.globals.GlobalAndroidViewModel
import com.marina.crudapp.usecases.main.MainViewModel
import com.marina.crudapp.usecases.insertmodifyuser.InsertModifyUserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val globalViewModel = module {
    factory { GlobalAndroidViewModel(androidContext() as App) }
}

val mainViewModel = module {
    factory { MainViewModel(androidContext() as App, get()) }
}

val insertModifyUserViewModel = module {
    factory { InsertModifyUserViewModel(androidContext() as App, get()) }
}
