package com.phonedeveloper.data.preferences

import com.phonedeveloper.domain.utils.Constants.Companion.SHARED_PREF_DARK_MODE
import com.phonedeveloper.domain.utils.Constants.Companion.SHARED_PREF_THEME

object UserPreferencesSingleton {
    private lateinit var sharPreferences: SharedPreferenceDataSource
    fun init(sharPreferences: SharedPreferenceDataSource) {
        this.sharPreferences = sharPreferences
    }
    /// theme
    fun setDarkModeSetting(darkMode: Int) {
        sharPreferences.setIntValue(SHARED_PREF_DARK_MODE, darkMode)
    }

    fun getDarkModeSetting() = sharPreferences.getIntValue(SHARED_PREF_DARK_MODE)

    fun setThemeSetting(darkMode: Int) {
        sharPreferences.setIntValue(SHARED_PREF_THEME, darkMode)
    }

    fun getThemeSetting() = sharPreferences.getIntValue(SHARED_PREF_THEME)

}