package com.example.myawesomepetproject.posts.data.model

import com.google.gson.annotations.SerializedName

class PostApiModel(@SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String)