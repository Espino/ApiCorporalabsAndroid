package com.phonedeveloper.domain.utils

import java.sql.Timestamp

class Constants {
    companion object {

        /**
         * Build.Gradle
         */
//        const val PUBLIC_KEY = "PUBLIC_KEY"
//        const val PRIVATE_KEY = "PRIVATE_KEY"
//        const val BASE_URL = "BASE_URL"

        /**
         * dataBase
         */
        const val DATABASE_NAME = "database_db"

        /**
         * BaseURL
         */
        const val API_KEY = "5eYoQJqjIS5QtvuWyuVkqRYLPP8plvik"
//        const val BASE_URL = "https://api.nytimes.com"
        const val apiKey: String = "?api-key=$API_KEY"


        val ts = Timestamp(System.currentTimeMillis()).time.toString()

        /// Theme
        const val SHARED_PREF_DARK_MODE = "SHARED_PREF_WATCH_PROVIDER_REGION"
        const val SHARED_PREF_THEME = "SHARED_PREF_THEME"
        const val SHARED_PREF_WATCH_PROVIDER_REGION = "regions_key"
        const val SHARED_PREF_WATCH_PROVIDER_MOVIE = "SHARED_PREF_WATCH_PROVIDER_MOVIE"

        //shared prefs
        const val SHARED_PREFS = "shared_prefs"
        const val PREF_IS_STARTED = "PREF_IS_STARTED"

        const val PREF_KEY_LAST_CACHE = "last_cache"

        const val PRIVATE_MODE = 0

        const val DEFAULT_NAME = "name"
        const val STARTING_PAGE_INDEX = 0
        const val NETWORK_PAGE_SIZE = 20
        const val PAGING_PER_PAGE = 20

        //        Detail
        const val DETAIL_ARG_KEY = "article"
    }

}