package com.fr1x3.onlinenewsapp.model

import android.os.Build
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)

@BindingAdapter("coverImage")
fun loadImage(imageView: ImageView, url: String?){

    url?.let {
        Glide.with(imageView.context)
                .load(url)
                .into(imageView)
    }

}