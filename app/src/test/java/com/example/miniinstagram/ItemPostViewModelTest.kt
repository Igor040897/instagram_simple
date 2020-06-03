package com.example.miniinstagram

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.example.miniinstagram.data.models.Post
import com.example.miniinstagram.ui.home.ItemPostViewModel
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(RobolectricTestRunner::class)
class ItemPostViewModelTest {
    private lateinit var context: Context
    private lateinit var viewModel: ItemPostViewModel

    @Before
    fun before() {
        context = ApplicationProvider.getApplicationContext()
        viewModel = ItemPostViewModel()
    }

    @Test
    fun testSetField() {
        val avatar = "https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/81/815da53f100038da1994e5dc76a2fef6b4c8a73c_full.jpg"
        val nickName = "milena_thebrowbar"
        val photoPlace = "The Brow Bar Kharkov"
        val usersLiked = arrayListOf("milena_trump", "lenka_taranets")
        val description = "Beauty @anna_zavadskaya \uD83D\uDDE8 \uD83D\uDE04 @covernumberone"
        val timePostCreated = "1591120800000"

        val post = Post(1, avatar, nickName, photoPlace, arrayListOf(), usersLiked, description, timePostCreated)
        viewModel.apply {
            start(post, context)

            TestCase.assertEquals(avatarField.get(), Uri.parse(avatar))
            TestCase.assertEquals(nickNameField.get(), nickName)
            TestCase.assertEquals(photoPlaceField.get(), photoPlace)
        }
    }
}