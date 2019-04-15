package com.example.myawesomepetproject.posts.domain.repository

import com.example.myawesomepetproject.posts.domain.model.Post
import io.reactivex.Single

interface PostsRepository {
  fun getPosts(): Single<List<Post>>
}