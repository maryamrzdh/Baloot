package com.example.baloot_maryammemarzadeh.repository.services

import com.example.baloot_maryammemarzadeh.model.ApiResponse
import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface APIService {

    @GET
    fun getNews( @Url url: String): Observable<ApiResponse>
}