package com.marina.crudapp.usecases.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.marina.crudapp.R
import com.marina.crudapp.databinding.ActivitySplashBinding
import com.marina.crudapp.extensions.goToActivity
import com.marina.crudapp.usecases.globals.GlobalActivity
import com.marina.crudapp.usecases.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : GlobalActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        //Simulate some services calls
        Handler(Looper.getMainLooper()).postDelayed({
            goToActivity<MainActivity>()
        }, 2000)

    }
}