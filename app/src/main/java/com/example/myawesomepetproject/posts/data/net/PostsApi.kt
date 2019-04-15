package com.example.myawesomepetproject.posts.data.net

import com.example.myawesomepetproject.posts.data.model.PostApiModel
import io.reactivex.Single

interface PostsApi {
  fun getPosts(): Single<List<PostApiModel>>
}