package com.example.miniinstagram.di.module.ui

import androidx.lifecycle.ViewModelProvider
import com.example.miniinstagram.MainActivity
import com.example.miniinstagram.MainActivityViewModel
import com.example.miniinstagram.di.module.RepositoryModule
import com.example.miniinstagram.di.module.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [RepositoryModule::class])
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [MainActivityViewModelModule::class])
    abstract fun providesMainActivity(): MainActivity
}

@Module
class MainActivityViewModelModule {
    @Provides
    fun postAddItemViewModel(
        viewModelFactory: ViewModelFactory,
        activity: MainActivity
    ): MainActivityViewModel {
        return ViewModelProvider(activity, viewModelFactory)[MainActivityViewModel::class.java]
    }
}