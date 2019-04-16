package com.example.feed.data.mapper

import com.example.base.di.scope.PerFeature
import com.example.base.mapper.Mapper
import com.example.feed.data.model.CommentApiModel
import com.example.feed.domain.model.Comment
import javax.inject.Inject

@PerFeature
class CommentMapper @Inject constructor(): Mapper<CommentApiModel, Comment>() {
  override fun reverse(to: Comment): CommentApiModel {
    TODO("not implemented")
  }
  
  override fun map(from: CommentApiModel): Comment {
    return with(from) {
      Comment(id, postId, name, email, body)
    }
  }
}