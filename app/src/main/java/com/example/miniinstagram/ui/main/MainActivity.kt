package com.example.miniinstagram.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.miniinstagram.R
import com.example.miniinstagram.databinding.ActivityMainBinding
import com.example.miniinstagram.ui.base.BaseActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val contentLayoutId = R.layout.activity_main

    @Inject
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun setupBinding(binding: ActivityMainBinding) {
        setSupportActionBar(toolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home,
            R.id.navigation_search,
            R.id.navigation_add_post,
            R.id.navigation_news,
            R.id.navigation_profile
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        setupOnClickListeners(binding)

        viewModel.start()
    }

    private fun setupOnClickListeners(binding: ActivityMainBinding) {
        binding.makePhotoImageView.setOnClickListener {
            Toast.makeText(this, "make photo", Toast.LENGTH_SHORT).show()
        }
        binding.messagesImageView.setOnClickListener {
            Toast.makeText(this, "show all messages", Toast.LENGTH_SHORT).show()
        }
    }
}