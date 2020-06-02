package com.example.miniinstagram.di.module

import android.app.Application
import com.example.miniinstagram.data.FileWorker
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FileWorkerModule {

    @Provides
    @Singleton
    fun provideFileWorker(application: Application): FileWorker {
        return FileWorker.getInstance(application)
    }
}