package com.example.myawesomepetproject.posts.data.mapper

import com.example.myawesomepetproject.base.mapper.Mapper
import com.example.myawesomepetproject.di.scope.PerFragment
import com.example.myawesomepetproject.posts.data.model.PostApiModel
import com.example.myawesomepetproject.posts.domain.model.Post
import javax.inject.Inject

@PerFragment
class PostsMapper @Inject constructor(): Mapper<PostApiModel, Post>() {
  override fun reverse(to: Post): PostApiModel {
    TODO("Not needed")
  }
  
  override fun map(from: PostApiModel): Post {
    return Post(from.id, from.title, from.body)
  }
}