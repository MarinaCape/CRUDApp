package com.marina.data.modules

import android.content.Context
import com.marina.data.cloud.ResponseHandler
import com.marina.data.cloud.interceptors.HeadersInterceptor
import com.marina.data.cloud.provideApi
import com.marina.data.cloud.provideHttpClient
import com.marina.data.cloud.provideRetrofit
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val networkModule = module {
    single { androidContext().getSharedPreferences("prefs", Context.MODE_PRIVATE) }
    single { HeadersInterceptor(get()) }
    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level =
            if (BuildConfig.BUILD_TYPE != "release") {
                HttpLoggingInterceptor.Level.BODY
            } else HttpLoggingInterceptor.Level.NONE
        httpLoggingInterceptor
    }

    factory { ResponseHandler() }

    single { provideHttpClient(get(), get() ) }
    single { provideApi(get()) }
    single { provideRetrofit(get()) }
}