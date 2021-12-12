package com.marina.data.cloud.interceptors

import android.content.SharedPreferences
import com.marina.data.extensions.log
import okhttp3.Interceptor
import okhttp3.Response


/**
 * Created by Javier Camarero on 14/05/2020.
 * QUADRAM MOBILE SOLUTIONS
 * jcamarero@quadram.mobi
 */
class HeadersInterceptor(private val prefs: SharedPreferences) : Interceptor {
    companion object {
        const val OS = "os"
        const val ANDROID = "android"
        const val BEARER = "Bearer "
        const val AUTHORIZATION = "Authorization"
        const val HEADER = "Header:"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader(OS, ANDROID)

        "$HEADER $OS = $ANDROID".log()

        //Implement here some kind of token autentificacion

        return chain.proceed(requestBuilder.build())
    }
}