package com.example.myawesomepetproject.details.presentation.mapper

import com.example.base.di.scope.PerFragment
import com.example.base.mapper.Mapper
import com.example.feed.domain.model.FullPost
import com.example.myawesomepetproject.details.presentation.model.FullPostViewModel
import javax.inject.Inject

@PerFragment
class FullPostViewModelMapper @Inject constructor(): Mapper<FullPost, FullPostViewModel>() {
  override fun reverse(to: FullPostViewModel): FullPost {
    TODO("not needed")
  }
  
  override fun map(from: FullPost): FullPostViewModel {
    return with(from) {
      FullPostViewModel(userId, postId, name, username, website, numberOfComments, title, body)
    }
  }
}