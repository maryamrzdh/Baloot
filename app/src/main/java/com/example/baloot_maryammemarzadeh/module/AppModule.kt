package com.mrz.irankart.module

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.baloot_maryammemarzadeh.room.AppDatabase
import com.example.baloot_maryammemarzadeh.room.DAOAccess

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

/**
 * The [AppModule] class provides objects to inject
 */
@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): DAOAccess {
        return appDatabase.appDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "RssReader"
        ).build()
    }

}