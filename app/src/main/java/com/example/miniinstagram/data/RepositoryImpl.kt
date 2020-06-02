package com.example.miniinstagram.data

import com.example.miniinstagram.data.models.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

class RepositoryImpl(
    private val fileWorker: FileWorker
) : Repository, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override val allPosts: Flow<List<Post>>
        get() = fileWorker.getPosts()

    companion object {
        @Volatile
        private var INSTANCE: RepositoryImpl? = null

        fun getInstance(fileWorker: FileWorker): Repository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: RepositoryImpl(fileWorker).also { INSTANCE = it }
            }
        }
    }
}