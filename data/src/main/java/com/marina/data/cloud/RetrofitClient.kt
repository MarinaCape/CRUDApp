package com.marina.data.cloud

import com.marina.data.BuildConfig.API_URL
import com.marina.data.cloud.interceptors.HeadersInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideHttpClient(
    interceptorBody: HttpLoggingInterceptor,
    headersInterceptor: HeadersInterceptor
): OkHttpClient {
    val client = OkHttpClient().newBuilder()
        .addInterceptor(interceptorBody)
        .addInterceptor(headersInterceptor)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
    return client.build()
}

fun provideApi(retrofit: Retrofit): RestApi =
    retrofit.create(RestApi::class.java)
