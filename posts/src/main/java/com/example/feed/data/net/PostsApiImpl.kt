package com.example.feed.data.net

import com.example.base.di.scope.PerFeature
import com.example.feed.data.model.CommentApiModel
import com.example.feed.data.model.PostApiModel
import io.reactivex.Single
import javax.inject.Inject

@PerFeature
class PostsApiImpl @Inject constructor(private val postsService: PostsService) : PostsApi {
  override fun getPost(postId: Int): Single<PostApiModel> {
    return postsService.getPost(postId)
  }
  
  override fun getComments(postId: Int): Single<List<CommentApiModel>> {
    return postsService.getComments(postId)
  }
  
  override fun getPosts(): Single<List<PostApiModel>> {
    return postsService.getPosts()
  }
}