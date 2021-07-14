package com.example.baloot_maryammemarzadeh.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.baloot_maryammemarzadeh.R
import com.example.baloot_maryammemarzadeh.databinding.ActivityMainBinding
import com.example.baloot_maryammemarzadeh.ui.main.PageViewModel
import com.example.baloot_maryammemarzadeh.ui.main.PlaceholderFragment
import com.example.baloot_maryammemarzadeh.ui.second.PlaceholderFragment2
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel:PageViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel= ViewModelProvider(this).get(PageViewModel::class.java)

//        if (savedInstanceState == null) {
//            val fragment = PlaceholderFragment()
//            binding.fragmentContainer
//            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
//                .commit()
//        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.firstTab -> {
                val fragment = PlaceholderFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.secondTab -> {
                val fragment = PlaceholderFragment2()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}