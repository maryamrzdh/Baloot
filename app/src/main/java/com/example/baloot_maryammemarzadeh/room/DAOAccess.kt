package com.example.baloot_maryammemarzadeh.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.baloot_maryammemarzadeh.model.NewsTableModel

@Dao
interface DAOAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(movieTableModel: NewsTableModel)
//
//
//    @Query("SELECT * FROM Movies")
//    fun getNewsFromDb() : LiveData<List<NewsTableModel>>

}