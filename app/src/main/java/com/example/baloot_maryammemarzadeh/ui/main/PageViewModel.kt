package com.example.baloot_maryammemarzadeh.ui.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baloot_maryammemarzadeh.model.Article
import com.example.baloot_maryammemarzadeh.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class PageViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {

    private var result=MutableLiveData<List<Article>?>()

    fun getNews() {
        repository.getNews().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .map {input->
                if (input.status.equals("ok"))
                    return@map input.articles
                else
                    return@map null
            }
            .subscribe(
                {it->result.value=it},
                { result.value=null }
            )
    }

    fun storeInDb(context: Context?,string: String){
        repository.insertData(context,string)
    }

    fun getResult(): MutableLiveData<List<Article>?> {
        return result
    }
}