package com.example.miniinstagram

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import java.util.concurrent.TimeUnit

fun ViewGroup.inflateView(@LayoutRes layout: Int): View {
    return LayoutInflater.from(this.context).inflate(layout, this, false)
}

fun Context.readFromAssets(filename: String): String {
    val reader = BufferedReader(InputStreamReader(assets.open(filename)))
    val sb = StringBuilder()
    var mLine: String? = reader.readLine()
    while (mLine != null) {
        sb.append(mLine)
        mLine = reader.readLine()
    }
    reader.close()
    return sb.toString()
}

fun Calendar.betweenDays(calendar: Calendar): Int {
    val date1: Date = calendar.time
    val date2: Date = time
    val diff = date2.time - date1.time
    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toInt()
}

fun Calendar.betweenHours(calendar: Calendar): Int {
    val date1: Date = calendar.time
    val date2: Date = time
    val diff = date2.time - date1.time
    return TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS).toInt()
}

fun Calendar.betweenMinutes(calendar: Calendar): Int {
    val date1: Date = calendar.time
    val date2: Date = time
    val diff = date2.time - date1.time
    return TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS).toInt()
}