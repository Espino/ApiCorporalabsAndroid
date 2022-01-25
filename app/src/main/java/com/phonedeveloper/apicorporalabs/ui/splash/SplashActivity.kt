package com.phonedeveloper.apicorporalabs.ui.splash

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import com.phonedeveloper.apicorporalabs.core.base.BaseActivity
import com.phonedeveloper.apicorporalabs.databinding.ActivitySplashBinding
import com.phonedeveloper.apicorporalabs.ui.main.MainActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private val splashTimeOut: Long = 1400

    override fun onActivityCreated() {
        Handler(Looper.getMainLooper()).postDelayed({
            Timber.d("Splash status")
            startActivity(MainActivity.intentBuilder(this))
            finish()
        }, splashTimeOut)
    }

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)
}