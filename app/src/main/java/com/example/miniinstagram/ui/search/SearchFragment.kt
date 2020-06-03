package com.example.miniinstagram.ui.search

import com.example.miniinstagram.R
import com.example.miniinstagram.databinding.SearchDashboardBinding
import com.example.miniinstagram.ui.base.BaseFragment

class SearchFragment : BaseFragment<SearchDashboardBinding>() {

    override val contentLayoutId = R.layout.search_dashboard

    private lateinit var searchViewModel: SearchViewModel

}