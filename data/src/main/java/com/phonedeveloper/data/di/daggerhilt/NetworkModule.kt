package com.phonedeveloper.data.di.daggerhilt

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.phonedeveloper.data.BuildConfig
import com.phonedeveloper.data.api.NYTimesAPIService
import com.phonedeveloper.data.di.MoshiDefault
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
//    @Provides
//    fun provideBaseUrl() = Urls.BASE_URL

    @Provides
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
//    fun provideOkHttpClient(loggingInterceptor: SupportInterceptor): OkHttpClient {
        val certificatePinner = CertificatePinner.Builder().add(
            "marvel.com",
            "sha256/79127babc1d3625ae552c54b34fdb53217137cefbce66f506bc9831cac6b3c4f"
        ).build()

        val okHttpClient = OkHttpClient().newBuilder()
//        okHttpClient.certificatePinner(certificatePinner)
        okHttpClient.callTimeout(40, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(40, TimeUnit.SECONDS)
        okHttpClient.readTimeout(40, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(40, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(loggingInterceptor)
//        okHttpClient.addInterceptor(SupportInterceptor)
//        okHttpClient.protocols(Collections.singletonList(Protocol.HTTP_1_1))
//        okHttpClient.build()
        return okHttpClient.build()
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    @Named("retrofit_api")
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
//        @MoshiNetwork moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun provideRestApiService(@Named("retrofit_api") retrofit: Retrofit): NYTimesAPIService {
        return retrofit.create(NYTimesAPIService::class.java)
    }

    @Provides
    @MoshiDefault
    fun provideMoshi(
        builder: Moshi.Builder
    ): Moshi = builder.build()

    @Provides
    fun provideMoshiBuilder() = Moshi.Builder()
}