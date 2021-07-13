package com.example.baloot_maryammemarzadeh.room

import android.content.Context
import androidx.room.*
import com.example.baloot_maryammemarzadeh.model.NewsTableModel

@Database(entities = [NewsTableModel::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDao() : DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDataseClient(context: Context) : AppDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, AppDatabase::class.java, "MOVIE_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}