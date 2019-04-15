package com.example.feed.data.mapper

import com.example.base.di.scope.PerFeature
import com.example.feed.data.model.PostApiModel
import com.example.feed.domain.model.Post
import javax.inject.Inject

@PerFeature
class PostsMapper @Inject constructor(): com.example.base.mapper.Mapper<PostApiModel, Post>() {
  override fun reverse(to: Post): PostApiModel {
    TODO("Not needed")
  }
  
  override fun map(from: PostApiModel): Post {
    return Post(from.id, from.userId, from.title, from.body)
  }
}