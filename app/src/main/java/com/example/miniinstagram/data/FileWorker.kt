package com.example.miniinstagram.data

import android.content.Context
import com.example.miniinstagram.data.models.Post
import com.example.miniinstagram.readFromAssets
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FileWorker(private val context: Context) {

    fun getPosts(): Flow<List<Post>> {
        val postListJson = readFromAssets("postList.json")
        val listType = object : TypeToken<List<Post>>() {}.type
        val posts = Gson().fromJson<List<Post>>(postListJson, listType)
        return flow {
            emit(posts)
        }
    }

    private fun readFromAssets(jsonFileName: String) = context.readFromAssets(jsonFileName)

    companion object {
        @Volatile
        private var INSTANCE: FileWorker? = null

        fun getInstance(context: Context): FileWorker {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: FileWorker(context).also { INSTANCE = it }
            }
        }
    }

}
