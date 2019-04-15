package com.example.myawesomepetproject.posts.presentation.mapper

import com.example.feed.domain.model.Post
import com.example.myawesomepetproject.posts.presentation.model.PostViewModel
import javax.inject.Inject

class PostViewModelMapper @Inject constructor(): com.example.base.mapper.Mapper<Post, PostViewModel>() {
  override fun reverse(to: PostViewModel): Post {
    TODO("not needed")
  }
  
  override fun map(from: Post): PostViewModel {
    return PostViewModel(from.id, from.userId, from.title, from.body)
  }
}