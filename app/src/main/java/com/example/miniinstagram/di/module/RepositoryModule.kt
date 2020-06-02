package com.example.miniinstagram.di.module

import com.example.miniinstagram.data.FileWorker
import com.example.miniinstagram.data.Repository
import com.example.miniinstagram.data.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [FileWorkerModule::class])
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(fileWorker: FileWorker): Repository {
        return RepositoryImpl.getInstance(fileWorker)
    }
}