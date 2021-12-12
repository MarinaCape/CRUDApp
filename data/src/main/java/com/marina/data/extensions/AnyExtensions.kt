package com.marina.data.extensions

import android.util.Log
import org.koin.android.BuildConfig

fun Any?.log(tag: String = "CRUD APP") {
    if (BuildConfig.BUILD_TYPE != "release")
        Log.d(tag, this.toString())
}
