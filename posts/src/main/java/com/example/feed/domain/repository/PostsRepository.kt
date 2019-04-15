package com.example.feed.domain.repository

import com.example.feed.domain.model.Post
import io.reactivex.Single

interface PostsRepository {
  fun getPosts(): Single<List<Post>>
}