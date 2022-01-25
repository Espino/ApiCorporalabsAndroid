package com.phonedeveloper.data.di.daggerhilt

import com.phonedeveloper.data.api.NYTimesAPIService
import com.phonedeveloper.data.repository.datasource.NewsRemoteDataSource
import com.phonedeveloper.data.repository.datasourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun providesRemoteDataSource(
        newsAPIService: NYTimesAPIService
    ): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(
            newsAPIService
        )
    }
}












