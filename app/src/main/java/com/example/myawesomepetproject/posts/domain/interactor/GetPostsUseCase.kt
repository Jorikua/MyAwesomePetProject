package com.example.myawesomepetproject.posts.domain.interactor

import com.example.myawesomepetproject.base.interactor.SingleUseCaseWithoutParams
import com.example.myawesomepetproject.posts.domain.model.Post
import com.example.myawesomepetproject.posts.domain.repository.PostsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val repository: PostsRepository) : SingleUseCaseWithoutParams<List<Post>>() {
  override fun buildUseCaseObservable(): Single<List<Post>> {
    return repository.getPosts()
  }
}