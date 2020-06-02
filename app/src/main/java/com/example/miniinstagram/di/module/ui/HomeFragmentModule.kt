package com.example.miniinstagram.di.module.ui

import androidx.lifecycle.ViewModelProvider
import com.example.miniinstagram.di.module.RepositoryModule
import com.example.miniinstagram.di.module.ViewModelFactory
import com.example.miniinstagram.ui.home.HomeFragment
import com.example.miniinstagram.ui.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [RepositoryModule::class])
abstract class HomeFragmentModule {
    @ContributesAndroidInjector(modules = [HomeViewModelModule::class])
    abstract fun homeFragment(): HomeFragment
}

@Module
class HomeViewModelModule {
    @Provides
    fun postHomeViewModel(
        viewModelFactory: ViewModelFactory,
        fragment: HomeFragment
    ): HomeViewModel {
        return ViewModelProvider(fragment, viewModelFactory)[HomeViewModel::class.java]
    }
}