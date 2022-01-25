package com.phonedeveloper.data.preferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.phonedeveloper.domain.utils.Constants
import com.phonedeveloper.domain.utils.Constants.Companion.PREF_KEY_LAST_CACHE

@SuppressLint("WrongConstant")
class SharedPreferenceDataSource (private val application: Context) : PreferenceDataSource {
    private val sharedPreferences: SharedPreferences by lazy {
        application.getSharedPreferences(Constants.SHARED_PREFS, Constants.PRIVATE_MODE)
    }

    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun getString(id: Int) = application.getString(id)

    fun getString(key: String, default: String? = null): String {
        return (sharedPreferences.get(key, default) ?: default)!!
    }

    fun putString(key: String, value: String?) {
        sharedPreferences.put(key, value)
    }

    fun getIntValue(key: String, default: Int = 0) =
        sharedPreferences.getInt(key, default)

    fun setIntValue(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun write() = sharedPreferences.edit()

    fun read() = sharedPreferences

    fun put(key: String, value: String) {
        editor.putString(key, value).apply()
    }

//    fun getString(key: String): String? {
//        return sharedPreferences.getString(key, null)
//    }

    fun put(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean? {
        return sharedPreferences.getBoolean(key, false)
    }

    fun clearAll() {
        editor.clear().apply()
    }

    /**
     * Store and retrieve the last time data was cached
     */
    var lastCacheTime: Long
        get() = sharedPreferences.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = sharedPreferences.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()

}

inline fun <reified T> SharedPreferences.get(key: String, defaultValue: T): T {
    when (T::class) {
        Boolean::class -> return this.getBoolean(key, defaultValue as Boolean) as T
        Float::class -> return this.getFloat(key, defaultValue as Float) as T
        Int::class -> return this.getInt(key, defaultValue as Int) as T
        Long::class -> return this.getLong(key, defaultValue as Long) as T
        String::class -> return this.getString(key, defaultValue as String) as T
        else -> {
            if (defaultValue is Set<*>) {
                return this.getStringSet(key, defaultValue as Set<String>) as T
            }
        }
    }

    return defaultValue
}

inline fun <reified T> SharedPreferences.put(key: String, value: T): T {
    val editor = this.edit()

    when (T::class) {
        Boolean::class -> editor.putBoolean(key, value as Boolean)
        Float::class -> editor.putFloat(key, value as Float)
        Int::class -> editor.putInt(key, value as Int)
        Long::class -> editor.putLong(key, value as Long)
        String::class -> editor.putString(key, value as String)
        else -> {
            if (value is Set<*>) {
                editor.putStringSet(key, value as Set<String>)
            }
        }
    }

    editor.apply()
    return value
}

fun String.validate():Boolean{
    if(this.trim().isEmpty()){
        return false
    }
    return true
}