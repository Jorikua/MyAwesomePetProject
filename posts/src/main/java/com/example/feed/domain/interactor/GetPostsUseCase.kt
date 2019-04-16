package com.example.feed.domain.interactor

import com.example.feed.domain.model.Post
import com.example.feed.domain.repository.PostsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val repository: PostsRepository) : com.example.base.interactor.SingleUseCaseWithoutParams<List<Post>>() {
  override fun buildUseCaseObservable(): Single<List<Post>> {
    return repository.getPosts()
  }
}