package com.example.myawesomepetproject.posts.presentation.mapper

import com.example.myawesomepetproject.base.mapper.Mapper
import com.example.myawesomepetproject.posts.domain.model.Post
import com.example.myawesomepetproject.posts.presentation.model.PostViewModel
import javax.inject.Inject

class PostViewModelMapper @Inject constructor(): Mapper<Post, PostViewModel>() {
  override fun reverse(to: PostViewModel): Post {
    TODO("not needed")
  }
  
  override fun map(from: Post): PostViewModel {
    return PostViewModel(from.id, from.title, from.body)
  }
}