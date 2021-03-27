package com.iti.elfarsisy.mad41.newsly.features.details.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.iti.elfarsisy.mad41.myapplication.util.MyApplication
import com.iti.elfarsisy.mad41.newsly.R
import com.iti.elfarsisy.mad41.newsly.data.model.ArticlesItem
import com.iti.elfarsisy.mad41.newsly.databinding.DetailsFragmentBinding
import com.iti.elfarsisy.mad41.newsly.features.details.viewmodel.ArticleDetailsViewModel
import com.iti.elfarsisy.mad41.newsly.features.details.viewmodel.ArticleDetailsViewModelFactory
import com.iti.elfarsisy.mad41.newsly.features.home.viewmodel.HomeViewModel
import com.iti.elfarsisy.mad41.newsly.features.home.viewmodel.HomeViewModelFactory
import timber.log.Timber

class ArticleDetailsFragment : Fragment() {
    private val args: ArticleDetailsragmentArgs by navArgs()
    private val viewModel by viewModels<ArticleDetailsViewModel>() {
        ArticleDetailsViewModelFactory(args.articleDetails )
    }
    private lateinit var binding: DetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.details_fragment, container, false)
        Timber.i("onCreateView Fragment")
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.mDetailsViewModel = viewModel
        binding.lifecycleOwner = this
    }

}