package com.phonedeveloper.apicorporalabs

import android.app.Application
import com.phonedeveloper.data.preferences.SharedPreferenceDataSource
import com.phonedeveloper.data.preferences.UserPreferencesSingleton
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import kotlin.time.ExperimentalTime

@ExperimentalTime
val sharedPrefHelper: SharedPreferenceDataSource by lazy { ApiCorporalabsApplication.prefs!! }
@ExperimentalTime
@HiltAndroidApp
class ApiCorporalabsApplication : Application(){

    private val applicationScope = CoroutineScope(Dispatchers.Default)
    //Shared Prefs
    companion object {
        var prefs: SharedPreferenceDataSource? = null
        var instance: ApiCorporalabsApplication? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        prefs = SharedPreferenceDataSource(applicationContext)
        UserPreferencesSingleton.init(prefs!!)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}