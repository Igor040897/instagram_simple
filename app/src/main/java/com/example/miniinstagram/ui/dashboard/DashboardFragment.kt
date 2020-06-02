package com.example.miniinstagram.ui.dashboard

import com.example.miniinstagram.R
import com.example.miniinstagram.databinding.FragmentDashboardBinding
import com.example.miniinstagram.ui.base.BaseFragment

class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {

    override val contentLayoutId = R.layout.fragment_dashboard

    private lateinit var dashboardViewModel: DashboardViewModel

}