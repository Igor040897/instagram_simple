package com.example.miniinstagram

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.example.miniinstagram.data.FileWorker
import com.example.miniinstagram.data.models.Post
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(RobolectricTestRunner::class)
class FileWorkerTest {

    private lateinit var context : Context
    private lateinit var fileWorker: FileWorker

    @Before
    fun before() {
        context = ApplicationProvider.getApplicationContext()
        fileWorker = FileWorker.getInstance(context)
    }

    @Test
    fun testGetPostList() {
        val postListJson = context.readFromAssets("postList.json")
        val listType = object : TypeToken<List<Post>>() {}.type
        val posts = Gson().fromJson<List<Post>>(postListJson, listType)
        GlobalScope.launch(Dispatchers.IO) {
            fileWorker.getPosts().collect {
                assert(posts == it)
            }
        }
    }
}