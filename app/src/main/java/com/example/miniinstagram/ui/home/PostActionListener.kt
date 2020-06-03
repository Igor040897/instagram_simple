package com.example.miniinstagram.ui.home

interface PostActionListener {
    fun onAvatarClick(nickname: String)
    fun onNickNameClick(nickname: String)
    fun onPhotoPlaceClick(place: String)
    fun onOptionsClick(postId: Long)
    fun onLikeClick(postId: Long)
    fun onCommentClick(postId: Long)
    fun onSendClick(postId: Long)
    fun onBookmarkClick(postId: Long)
    fun onLinkClick(link: String)
}