package com.example.miniinstagram.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    val avatarUrl: String,
    val nickName: String,
    val photoPlace: String,
    val images: List<String>,
    val usersLiked: List<String>,
    val description: String,
    val date : String
) : Parcelable