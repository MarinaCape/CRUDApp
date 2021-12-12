package com.marina.crudapp.usecases.globals

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marina.crudapp.App
import com.marina.crudapp.R
import com.marina.crudapp.extensions.showAlertDialog

abstract class GlobalActivity : AppCompatActivity() {

    lateinit var app: App

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
        app = applicationContext as App
    }


    fun showAlert(message: String?) {
        showAlertDialog(contentText = message, positiveText = getString(R.string.accept))
    }

}