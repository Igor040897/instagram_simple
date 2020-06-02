package com.example.miniinstagram.di.component

import android.app.Application
import com.example.miniinstagram.App
import com.example.miniinstagram.di.module.FileWorkerModule
import com.example.miniinstagram.di.module.RepositoryModule
import com.example.miniinstagram.di.module.ViewModelFactoryModule
import com.example.miniinstagram.di.module.ui.HomeFragmentModule
import com.example.miniinstagram.di.module.ui.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        FileWorkerModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
        MainActivityModule::class,
        HomeFragmentModule::class
    ]
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: App)
}