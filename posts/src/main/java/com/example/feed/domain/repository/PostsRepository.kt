package com.example.feed.domain.repository

import com.example.feed.domain.model.Comment
import com.example.feed.domain.model.Post
import io.reactivex.Single

interface PostsRepository {
  fun getPosts(): Single<List<Post>>
  fun getPost(postId: Int): Single<Post>
  fun getCommentsByPostId(postId: Int): Single<List<Comment>>
}