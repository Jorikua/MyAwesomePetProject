package com.example.myawesomepetproject.posts.data.net

import com.example.myawesomepetproject.posts.data.model.PostApiModel
import io.reactivex.Single
import retrofit2.http.GET

interface PostsService {
  @GET("posts")
  fun getPosts(): Single<List<PostApiModel>>
}