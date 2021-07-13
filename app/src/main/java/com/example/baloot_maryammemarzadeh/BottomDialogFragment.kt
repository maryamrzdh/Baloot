package com.example.baloot_maryammemarzadeh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baloot_maryammemarzadeh.databinding.BottomSheetBinding
import com.example.baloot_maryammemarzadeh.databinding.FragmentSecondBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomDialogFragment constructor(var text:String) : BottomSheetDialogFragment() {

    companion object {

        const val TAG = "CustomBottomSheetDialogFragment"
        lateinit var binding: BottomSheetBinding

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         binding=BottomSheetBinding.inflate(inflater, container, false)

        binding.aboutMe=text

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


//        firstButton.setOnClickListener {
//            //handle click event
//            Toast.makeText(context, "First Button Clicked", Toast.LENGTH_SHORT).show()
//        }
//        secondButton.setOnClickListener {
//            //handle click event
//            Toast.makeText(context, "Second Button Clicked", Toast.LENGTH_SHORT).show()
//        }
//        thirdButton.setOnClickListener {
//            //handle click event
//            Toast.makeText(context, "Third Button Clicked", Toast.LENGTH_SHORT).show()
//        }

    }
}