package com.marina.data.modules

import com.marina.data.Repository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

var repositoryModule = module {
    factory { Dispatchers.IO }
    factory { Repository(get(), get(), get(), get()) }
}