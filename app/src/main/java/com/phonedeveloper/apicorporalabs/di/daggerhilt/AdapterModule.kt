package com.phonedeveloper.apicorporalabs.di.daggerhilt

import com.phonedeveloper.apicorporalabs.ui.adapter.ListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
    @Singleton
    @Provides
    fun provideListMarvelAdapter(): ListAdapter {
        return ListAdapter()
    }
}