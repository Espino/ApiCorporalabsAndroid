package com.phonedeveloper.data.di.daggerhilt

import androidx.paging.ExperimentalPagingApi
import com.phonedeveloper.data.repository.NewsRepositoryImpl
import com.phonedeveloper.data.repository.datasource.NewsRemoteDataSource
import com.phonedeveloper.domain.repository.RepositoryDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton


@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @ExperimentalCoroutinesApi
    @Singleton
    @Provides
    fun provideRepositoryDataStore(
        newsRemoteDataSource: NewsRemoteDataSource
    ): RepositoryDataStore {
        return NewsRepositoryImpl(
            newsRemoteDataSource
        )
    }
}














