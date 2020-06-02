package com.example.miniinstagram.ui.home

import android.net.Uri
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.miniinstagram.data.models.Post

class ItemPostViewModel : ViewModel() {

    val avatar = ObservableField<Uri>()
    val nickName = ObservableField<String>()
    val photoPlace = ObservableField<String>()

    //todo spanable
    val usersLiked = ObservableField<String>()
    val description = ObservableField<String>()

    val timePostCreated = ObservableField<String>()

    fun start(post: Post) {
        avatar.set(Uri.parse(post.avatarUrl))
        nickName.set(post.nickName)
        photoPlace.set(post.photoPlace)
        usersLiked.set(post.usersLiked.toString())
        description.set(post.description)
        timePostCreated.set(post.date)
    }
}