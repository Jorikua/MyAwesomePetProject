package com.example.feed.data.net

import com.example.feed.data.model.CommentApiModel
import com.example.feed.data.model.PostApiModel
import io.reactivex.Single

interface PostsApi {
  fun getPosts(): Single<List<PostApiModel>>
  fun getPost(postId: Int): Single<PostApiModel>
  fun getComments(postId: Int): Single<List<CommentApiModel>>
}