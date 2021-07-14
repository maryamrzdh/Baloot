package com.example.baloot_maryammemarzadeh.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.baloot_maryammemarzadeh.databinding.FragmentDetailsBinding
import com.example.baloot_maryammemarzadeh.model.Article
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding? = null
    private val args: DetailsFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//         article= arguments!!.getParcelable("art")!!
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
        args.article.let {
            article = it
        }
        binding!!.article= article
        var mImageView = binding!!.img
        Picasso.get().load(article.urlToImage).into(mImageView)

    }
    companion object {
        lateinit var article: Article
    }
}