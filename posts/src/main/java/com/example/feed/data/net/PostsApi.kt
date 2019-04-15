package com.example.feed.data.net

import com.example.feed.data.model.PostApiModel
import io.reactivex.Single

interface PostsApi {
  fun getPosts(): Single<List<PostApiModel>>
}