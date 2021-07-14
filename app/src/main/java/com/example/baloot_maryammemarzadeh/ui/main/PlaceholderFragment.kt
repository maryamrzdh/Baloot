package com.example.baloot_maryammemarzadeh.ui.main

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baloot_maryammemarzadeh.PaginationScrollListener
import com.example.baloot_maryammemarzadeh.R
import com.example.baloot_maryammemarzadeh.databinding.FragmentMainBinding
import com.example.baloot_maryammemarzadeh.model.Article
import dagger.hilt.android.AndroidEntryPoint

/**
 * A placeholder fragment containing a simple view.
 */
@AndroidEntryPoint
class PlaceholderFragment : Fragment(),RecyclerAdapter.ClickListener {

    private lateinit var pageViewModel: PageViewModel
    private var binding: FragmentMainBinding? = null
//    lateinit var adapterAdapter:RecyclerAdapter
    lateinit var observer:Observer<List<Article>?>
    var page=1

    var isLastPage: Boolean = false
    var loading: Boolean = false

    var articleList=ArrayList<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(requireActivity()).get(PageViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater, container, false)
        showLoading()
        pageViewModel.getNews(page)
        observe()

        return binding!!.root
    }

    companion object {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        var loading = true
        var pastVisibleItems: Int
        var visibleItemCount: Int
        var totalItemCount: Int

        val mLayoutManager = LinearLayoutManager(context)

        binding!!.recycler.apply {
            layoutManager = mLayoutManager
            adapter = RecyclerAdapter(articleList,this@PlaceholderFragment)

        }

        binding!!.recycler.addOnScrollListener(object : PaginationScrollListener(mLayoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return loading
            }

            override fun loadMoreItems() {
                loading = true
                //you have to call loadmore items to get more data
                page++
                pageViewModel.getNews(page)
            }
        })
//        binding!!.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                if (dy > 0) { //check for scroll down
//                    visibleItemCount = mLayoutManager.childCount
//                    totalItemCount = mLayoutManager.itemCount
//                    pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition()
//                    if (loading) {
//                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
//                            loading = false
//                            Log.v("...", "Last Item Wow !")
//                            // Do pagination.. i.e. fetch new data
//                            page++
//                            pageViewModel.getNews(page)
//                            loading = true
//                        }
//                    }
//                }
//            }
//        })
    }

    override fun onItemClick(position: Int) {
        val article=articleList[position]
        val bundle= bundleOf("art" to article)
        val action = PlaceholderFragmentDirections.actionPlaceholderFragmentToDetailsFragment2(article)
        findNavController().navigate(action)
    }

    private fun observe(){

         observer = Observer<List<Article>?> {
            hideLoading()
            if (it != null) {
                articleList.addAll(it)

//                if(articleList.isEmpty())
//                    articleList= it as ArrayList<Article>
//                else
//                    articleList.addAll(it)
//                adapterAdapter = RecyclerAdapter(articleList,this@PlaceholderFragment)

                binding!!.recycler.adapter!!.notifyDataSetChanged()

                for (i in it)
                    pageViewModel.storeInDb(context, i.title!!, i.author!!, i.publishedAt!!)
            } else
                Toast.makeText(activity, "error fetching data!", Toast.LENGTH_SHORT).show()
            pageViewModel.reset()
        }
        pageViewModel.getResult().observe(requireActivity(),observer)
    }

    private var progress: ProgressDialog? = null
    /**
     * Show loading.
     */
    private fun showLoading() {
        hideLoading()
        progress = showLoadingDialog(activity)
    }

    /**
     * Hide loading.
     */
    private fun hideLoading() {
        if (progress != null && progress!!.isShowing()) {
            progress!!.cancel()
        }
    }

    open fun showLoadingDialog(context: Context?): ProgressDialog? {
        val progressDialog = ProgressDialog(context)
        progressDialog.show()
        if (progressDialog.window != null) {
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.setContentView(R.layout.dialog_progress)
        progressDialog.isIndeterminate = true
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        return progressDialog
    }


    override fun onResume() {
        super.onResume()
        isNetworkConnected()
    }
    fun isNetworkConnected():Boolean {
        val connectivityManager = requireActivity().getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.state == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.state == NetworkInfo.State.CONNECTED
    }
}