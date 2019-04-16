package com.example.feed.domain.model

class FullPost(val userId: Int,
    val postId: Int,
    val name: String,
    val username: String,
    val website: String,
    val numberOfComments: Int,
    val title: String,
    val body: String)