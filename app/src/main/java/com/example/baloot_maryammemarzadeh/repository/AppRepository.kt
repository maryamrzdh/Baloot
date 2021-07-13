package com.example.baloot_maryammemarzadeh.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.baloot_maryammemarzadeh.model.ApiResponse
import com.example.baloot_maryammemarzadeh.model.NewsTableModel
import com.example.baloot_maryammemarzadeh.repository.services.APIService
import com.example.baloot_maryammemarzadeh.room.AppDatabase
import com.example.baloot_maryammemarzadeh.room.DAOAccess
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class AppRepository(private var apiService: APIService, private var database: AppDatabase) {

    fun insertData(context: Context?, id: String) {
        CoroutineScope(IO).launch {
            val movieDetails = NewsTableModel(id,"","","","")
            database.appDao().insertData(movieDetails)
        }
    }
//
//    fun getNewsFromDb(context: Context?) : LiveData<List<NewsTableModel>> {
//        return database.appDao().getNewsFromDb()
//    }


    //server
    fun getNews(): Observable<ApiResponse> {
        return apiService.getNews()
    }

}