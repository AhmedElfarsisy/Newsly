package com.iti.elfarsisy.mad41.newsly.features.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.iti.elfarsisy.mad41.myapplication.util.MyApplication
import com.iti.elfarsisy.mad41.newsly.R
import com.iti.elfarsisy.mad41.newsly.data.model.ArticlesItem
import com.iti.elfarsisy.mad41.newsly.data.repository.NewsRepository
import com.iti.elfarsisy.mad41.newsly.databinding.HomeFragmentBinding
import com.iti.elfarsisy.mad41.newsly.features.home.viewmodel.HomeViewModel
import com.iti.elfarsisy.mad41.newsly.features.home.viewmodel.HomeViewModelFactory
import timber.log.Timber

class HomeFragment : Fragment() {

    //fragment Extension
    private val homeViewModel by viewModels<HomeViewModel> {
        HomeViewModelFactory((requireActivity().application as MyApplication).newsRepository)
    }


    private lateinit var binding: HomeFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        Timber.i("onCreateView Fragment")
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.mHomeViewModel = homeViewModel
        binding.lifecycleOwner = this
    }


}