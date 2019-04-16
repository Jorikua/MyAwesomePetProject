package com.example.feed.data.net

import com.example.feed.data.model.CommentApiModel
import com.example.feed.data.model.PostApiModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostsService {
  @GET("posts")
  fun getPosts(): Single<List<PostApiModel>>
  
  @GET("posts/{id}")
  fun getPost(@Path("id") id: Int): Single<PostApiModel>
  
  @GET("comments")
  fun getComments(@Query("postId") postId: Int): Single<List<CommentApiModel>>
}