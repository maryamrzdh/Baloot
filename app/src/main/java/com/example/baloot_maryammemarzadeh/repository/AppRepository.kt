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

    fun insertData(context: Context?, title: String,author:String,year:String) {
        CoroutineScope(IO).launch {
            val article = NewsTableModel(title,author,year)
            database.appDao().insertData(article)
        }
    }
//
//    fun getNewsFromDb(context: Context?) : LiveData<List<NewsTableModel>> {
//        return database.appDao().getNewsFromDb()
//    }


    fun getNews(page :Int): Observable<ApiResponse> {
        val url = "v2/everything?q=tesla&from=2021-06-13&pageSize=20&page=$page&sortBy=publishedAt&apiKey=10ea5b0d346a426283f5191f9cad3142"
//        val url = "v2/top-headlines?sources=techcrunch&apiKey=b59977a0398b41af9b69d1de701a5f20"
        return apiService.getNews(url)
    }

}