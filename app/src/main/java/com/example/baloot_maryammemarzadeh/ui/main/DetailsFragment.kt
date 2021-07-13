package com.example.baloot_maryammemarzadeh.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.NavController
import com.example.baloot_maryammemarzadeh.databinding.FragmentDetailsBinding
import com.example.baloot_maryammemarzadeh.model.Article
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         article= arguments!!.getParcelable("art")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      binding!!.article= article
        var mImageView = binding!!.img
        Picasso.get().load(article.urlToImage).into(mImageView)


//        @BindingAdapter("urlImage")
//        fun bindUrlImage(view: ImageView, imageUrl: String?) {
//            if (imageUrl != null) {
//                Picasso.get()
//                    .load(imageUrl)
//                    .fit()
//                    .centerCrop()
//                    .into(view)
//            } else {
//                view.setImageBitmap(null)
//            }
//        }


    }
    companion object {
        lateinit var article: Article
    }
}