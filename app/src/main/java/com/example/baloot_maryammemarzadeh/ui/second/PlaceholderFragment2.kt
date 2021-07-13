package com.example.baloot_maryammemarzadeh.ui.second

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.baloot_maryammemarzadeh.BottomDialogFragment
import com.example.baloot_maryammemarzadeh.R
import com.example.baloot_maryammemarzadeh.databinding.FragmentSecondBinding
import com.example.baloot_maryammemarzadeh.model.User
import com.example.baloot_maryammemarzadeh.ui.main.PageViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


/**
 * A placeholder fragment containing a simple view.
 */
@AndroidEntryPoint
class PlaceholderFragment2 : Fragment() {

    private lateinit var pageViewModel: PageViewModel2
    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel2::class.java).apply {
//            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val user = User(
            "maryam memarzadeh",
            "https://www.linkedin.com/in/mmemarzadeh94/",
            getString(R.string.about)
        )
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        binding.user = user
        binding.viewModel = pageViewModel

        binding.button.setOnClickListener {
            BottomDialogFragment(user.aboutMe).apply {
                show(requireActivity().supportFragmentManager, BottomDialogFragment.TAG)
            }
        }

        binding.user = user
        binding.link.setOnClickListener {
            fun onLinkedClick() {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(user.Linkedin))
                startActivity(browserIntent)
            }
        }
        return binding.root
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
        fun newInstance(sectionNumber: Int): PlaceholderFragment2 {
            return PlaceholderFragment2().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
