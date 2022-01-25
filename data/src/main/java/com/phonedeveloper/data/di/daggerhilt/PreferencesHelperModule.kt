package com.phonedeveloper.data.di.daggerhilt

import android.content.Context
import com.phonedeveloper.data.preferences.SharedPreferenceDataSource
import com.phonedeveloper.data.preferences.UserPreferencesSingleton
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferencesHelperModule {

    @Singleton
    @Provides
    fun provideUserPreferencesSingleton() = UserPreferencesSingleton

    @Singleton
    @Provides
    fun providePreferencesHelper(@ApplicationContext appContext: Context): SharedPreferenceDataSource {
        return SharedPreferenceDataSource(appContext)
    }

}