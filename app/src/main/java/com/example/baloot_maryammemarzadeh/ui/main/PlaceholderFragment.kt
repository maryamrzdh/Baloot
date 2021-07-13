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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    lateinit var adapter:RecyclerAdapter
    private lateinit var nav_controler:NavController

    var articleList=ArrayList<Article>()

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
//            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater, container, false)
        observe()
        pageViewModel.getNews()
        showLoading()

        return binding!!.root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(list: ArrayList<Article>): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
//                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var loading = true
        var pastVisibleItems: Int
        var visibleItemCount: Int
        var totalItemCount: Int

        val mLayoutManager = LinearLayoutManager(context)
        binding!!.recycler.layoutManager = mLayoutManager


//        nav_controler=Navigation.findNavController(view)

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController



        binding!!.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = mLayoutManager.childCount
                    totalItemCount = mLayoutManager.itemCount
                    pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            loading = false
                            Log.v("...", "Last Item Wow !")
                            // Do pagination.. i.e. fetch new data
                            pageViewModel.getNews()
                            observe()
                            loading = true
                        }
                    }
                }
            }
        })
    }

    override fun onItemClick(position: Int) {
        val article=articleList[position]
        val bundle= bundleOf("art" to article)
        nav_controler.navigate(R.id.action_placeholderFragment_to_detailsFragment2,bundle)
    }

    fun observe(){
        pageViewModel.getResult().observe(viewLifecycleOwner,{it->
            hideLoading()
            if (it!=null){
                articleList= it as ArrayList<Article>
                binding!!.recycler.apply {
//                    layoutManager = LinearLayoutManager(context)
                    // set the custom adapter to the RecyclerView
                    adapter = RecyclerAdapter(it,this@PlaceholderFragment)
                }
                for (i in it)
                    pageViewModel.storeInDb(context,i.title!!,i.author!!,i.publishedAt!!)
            }
            else
                Toast.makeText(activity,"error fetching data!", Toast.LENGTH_SHORT).show()
        })
    }

    private var progress: ProgressDialog? = null
    /**
     * Show loading.
     */
    open fun showLoading() {
        hideLoading()
        progress = showLoadingDialog(activity)
    }

    /**
     * Hide loading.
     */
    open fun hideLoading() {
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
        observe()
    }
    fun isNetworkConnected():Boolean {
        val connectivityManager = requireActivity().getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.state == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.state == NetworkInfo.State.CONNECTED
    }
}