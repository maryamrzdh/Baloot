package com.example.baloot_maryammemarzadeh.ui.second

import androidx.lifecycle.ViewModel
import com.example.baloot_maryammemarzadeh.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PageViewModel2 @Inject constructor(private val repository: AppRepository) : ViewModel() {

//    fun onAboutClicked(){
//        BottomDialogFragment().apply {
//            show(activity!!.supportFragmentManager, BottomDialogFragment.TAG)
//        }

//    }

//    fun onLinkedClick(){
//        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/mmemarzadeh94/"))
//        startActivity(browserIntent)
//    }
}