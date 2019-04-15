package com.example.feed.data.net

import com.example.feed.data.model.PostApiModel
import io.reactivex.Single
import retrofit2.http.GET

interface PostsService {
  @GET("posts")
  fun getPosts(): Single<List<PostApiModel>>
}