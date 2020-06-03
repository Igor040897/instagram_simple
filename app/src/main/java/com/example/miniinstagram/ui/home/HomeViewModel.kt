package com.example.miniinstagram.ui.home

import android.app.Application
import com.example.miniinstagram.data.Repository
import com.example.miniinstagram.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel (
    application: Application,
    private val repository: Repository
) : BaseViewModel(application) {

    val adapter = PostAdapter()
    var job: Job = launch(Dispatchers.IO) {
        repository.allPosts.collect {
            launch(Dispatchers.Main) {
                adapter.setItems(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}