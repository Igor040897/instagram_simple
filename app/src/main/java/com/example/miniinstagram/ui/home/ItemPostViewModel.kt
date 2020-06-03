package com.example.miniinstagram.ui.home

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.miniinstagram.R
import com.example.miniinstagram.betweenDays
import com.example.miniinstagram.betweenHours
import com.example.miniinstagram.betweenMinutes
import com.example.miniinstagram.data.models.Post
import java.util.*
import kotlin.collections.ArrayList


class ItemPostViewModel : ViewModel() {

    val linkCommand = MutableLiveData<String>()

    val avatarField = ObservableField<Uri>()
    val nickNameField = ObservableField<String>()
    val photoPlaceField = ObservableField<String>()
    val usersLikedField = ObservableField<SpannableStringBuilder>()
    val descriptionField = ObservableField<SpannableStringBuilder>()
    val timePostCreatedField = ObservableField<String>()

    fun start(post: Post, context: Context) {
        avatarField.set(Uri.parse(post.avatarUrl))
        nickNameField.set(post.nickName)
        photoPlaceField.set(post.photoPlace)
        usersLikedField.set(setupUsersLikedField(ArrayList(post.usersLiked), context))
        descriptionField.set(setupDescriptionField(post.nickName, post.description))
        timePostCreatedField.set(setupTimePassed(post.date, context))
    }

    private fun setupTimePassed(date: String, context: Context): String {
        var result = ""
        val postCreated = Calendar.getInstance().apply { time = Date(date.toLong()) }
        val currentTime = Calendar.getInstance()
        val countDays = currentTime.betweenDays(postCreated)

        when {
            countDays == 0 -> {
                val betweenHours = currentTime.betweenHours(postCreated)
                result = context.resources.run {
                    if (betweenHours == 0) {
                        val betweenMinutes = currentTime.betweenMinutes(postCreated)
                        getQuantityString(R.plurals.hour_count_ago, betweenMinutes, betweenMinutes)
                    } else getQuantityString(R.plurals.hour_count_ago, betweenHours, betweenHours)
                }
            }
            countDays >= 1 -> {
                result = context.resources.getQuantityString(R.plurals.day_count_ago, countDays, countDays)
            }
        }

        return result
    }

    private fun getBoldSpannable(text: String): SpannableString {
        val textSpannable = SpannableString(text)
        textSpannable.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            textSpannable.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return textSpannable
    }

    private fun setupUsersLikedField(users: ArrayList<String>, context: Context): SpannableStringBuilder {
        fun getTwoUsersBoldSpannable(): SpannableString {
            val twoUsers = users.take(2).toString().let {
                it.substring(1, it.length - 1)
            }
            return getBoldSpannable(twoUsers)
        }

        val completeText = SpannableStringBuilder("${context.getText(R.string.liked_by)} ")
        when {
            users.size > 2 -> {
                val twoUsersBoldSpannable = getTwoUsersBoldSpannable()
                val countOtherUsers = context.resources.getQuantityString(
                    R.plurals.users_others,
                    users.size - 1,
                    users.size - 1
                )
                val countOtherUsersBoldSpannable = getBoldSpannable(countOtherUsers)
                completeText.append(twoUsersBoldSpannable)
                completeText.append(" ${context.getText(R.string.and)} ")
                completeText.append(countOtherUsersBoldSpannable)
            }
            users.size == 2 -> {
                val twoUsersBoldSpannable = getTwoUsersBoldSpannable()
                completeText.append(twoUsersBoldSpannable)
            }
            users.size > 0 -> {
                val allUsers = users.toString().substring(1, users.size - 1)
                val usersBoldSpannable = getBoldSpannable(allUsers)
                completeText.append(usersBoldSpannable)
            }
            else -> {
                completeText.clear()
            }
        }
        return completeText
    }

    private fun setupDescriptionField(author: String, description: String): SpannableStringBuilder {
        val completeText = SpannableStringBuilder()

        val authorBoldSpannable = getBoldSpannable("$author ")
        val textWithLinks = setupSpanForLinks(description)

        completeText.append(authorBoldSpannable)
        completeText.append(textWithLinks)
        return completeText
    }

    private fun setupSpanForLinks(text: String): SpannableStringBuilder {
        val completeText = SpannableStringBuilder(text)

        fun setLink(start: Int, end: Int) {
            completeText.setSpan(
                object : ClickableSpan() {
                    override fun onClick(textView: View) {
                        val spanned = (textView as TextView).text as Spanned
                        val startIndex = spanned.getSpanStart(this)
                        val endIndex = spanned.getSpanEnd(this)
                        linkCommand.value = spanned.subSequence(startIndex, endIndex).toString()
                    }
                },
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            completeText.setSpan(
                ForegroundColorSpan(Color.BLUE),
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );
        }

        var findLink = false
        var indexStartLink = -1
        completeText.forEachIndexed { index, c ->
            if (findLink) {
                if (c.toString() == " " || (completeText.length - 1) == index) {
                    val indexEndLink = if ((completeText.length - 1) == index) index + 1 else index
                    setLink(indexStartLink, indexEndLink)
                    findLink = false
                    indexStartLink = -1
                }
            } else {
                when (c.toString()) {
                    "#", "@" -> {
                        findLink = true
                        indexStartLink = index
                    }
                }
            }
        }
        return completeText
    }
}