package com.phonedeveloper.data.di

import javax.inject.Qualifier

/**
 * Coroutines Dispatchers
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DefaultDispatcher

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MainDispatcher


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MoshiNetwork

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MoshiDefault

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class PublicKey

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class PrivateKey
