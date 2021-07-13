package com.example.baloot_maryammemarzadeh.repository.services

import com.example.baloot_maryammemarzadeh.model.ApiResponse
import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface APIService {

    /**
     *
     */

    @GET("v2/everything?q=tesla&from=2021-06-13&sortBy=publishedAt&apiKey=b59977a0398b41af9b69d1de701a5f20")
    fun getNews(): Observable<ApiResponse>
}