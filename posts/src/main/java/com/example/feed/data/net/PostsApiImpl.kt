package com.example.feed.data.net

import com.example.base.di.scope.PerFeature
import com.example.feed.data.model.PostApiModel
import io.reactivex.Single
import javax.inject.Inject

@PerFeature
class PostsApiImpl @Inject constructor(private val postsService: PostsService) : PostsApi {
  override fun getPosts(): Single<List<PostApiModel>> {
    return postsService.getPosts()
  }
}