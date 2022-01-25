package com.phonedeveloper.apicorporalabs.di.daggerhilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class SettingsDataModule {

    @Provides
    fun providesStringManager(): String { return toString()
    }

}












