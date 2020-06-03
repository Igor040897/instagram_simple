package com.example.miniinstagram.ui.main

import android.app.Application
import com.example.miniinstagram.data.Repository
import com.example.miniinstagram.ui.base.BaseViewModel

class MainActivityViewModel (
    application: Application,
    private val repository: Repository
) : BaseViewModel(application) {
    fun start() {

    }
}