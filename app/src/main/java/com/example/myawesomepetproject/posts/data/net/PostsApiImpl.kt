package com.example.myawesomepetproject.posts.data.net

import com.example.myawesomepetproject.di.scope.PerFragment
import com.example.myawesomepetproject.posts.data.model.PostApiModel
import io.reactivex.Single
import javax.inject.Inject

@PerFragment
class PostsApiImpl @Inject constructor(private val postsService: PostsService) : PostsApi {
  override fun getPosts(): Single<List<PostApiModel>> {
    return postsService.getPosts()
  }
}