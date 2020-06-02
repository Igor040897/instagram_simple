package com.example.miniinstagram.ui.home

import android.content.Context
import com.example.miniinstagram.R
import com.example.miniinstagram.databinding.FragmentHomeBinding
import com.example.miniinstagram.ui.base.BaseFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val contentLayoutId = R.layout.fragment_home

    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun setupBinding(binding: FragmentHomeBinding) {
        super.setupBinding(binding)
        binding.viewModel = homeViewModel
    }
}