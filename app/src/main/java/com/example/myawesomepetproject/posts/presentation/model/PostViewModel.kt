package com.example.myawesomepetproject.posts.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostViewModel(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
): Parcelable