package com.example.feed.domain.model

class Comment(val id: Int,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String)