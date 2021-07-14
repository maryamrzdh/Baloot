package com.example.baloot_maryammemarzadeh.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.example.baloot_maryammemarzadeh.repository.AppRepository
import com.example.baloot_maryammemarzadeh.repository.services.APIService
import com.example.baloot_maryammemarzadeh.room.AppDatabase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class APIModule {

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor { chain ->
            val originalRequest = chain.request()
            val builder = originalRequest.newBuilder()
                    .header("Content-Type", "application/json")
            val newRequest = builder.build()
            chain.proceed(newRequest)
        }.build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsRepository(apiService: APIService,database: AppDatabase): AppRepository {
        return AppRepository(apiService,database)
    }

    companion object {
        private const val BASE_URL = "https://newsapi.org/"

    }
}