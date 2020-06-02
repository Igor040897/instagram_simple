package com.example.miniinstagram.data

import com.example.miniinstagram.data.models.Post
import kotlinx.coroutines.flow.Flow

interface Repository {
    val allPosts: Flow<List<Post>>
}