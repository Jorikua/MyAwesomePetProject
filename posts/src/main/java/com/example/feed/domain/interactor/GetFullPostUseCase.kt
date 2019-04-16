package com.example.feed.domain.interactor

import com.example.base.interactor.SingleUseCase
import com.example.feed.domain.model.Comment
import com.example.feed.domain.model.FullPost
import com.example.feed.domain.model.Post
import com.example.feed.domain.repository.PostsRepository
import com.example.user.domain.model.User
import com.example.user.domain.repository.UserRepository
import io.reactivex.Single
import io.reactivex.functions.Function3
import javax.inject.Inject

class GetFullPostUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val postsRepository: PostsRepository) : SingleUseCase<FullPost, GetFullPostUseCase.Params>() {
  override fun buildUseCaseObservable(params: Params): Single<FullPost> {
    val postSingle = postsRepository.getPost(params.postId)
    val userSingle = userRepository.getUser(params.userId)
    val commentsSingle = postsRepository.getCommentsByPostId(params.postId)
    return Single.zip(postSingle,
        userSingle,
        commentsSingle,
        Function3<Post, User, List<Comment>, FullPost> { post, user, listOfComments ->
          FullPost(user.id, post.id, user.name, user.username, user.website, listOfComments.size, post.title, post.body)
        })
  }
  
  class Params(val userId: Int, val postId: Int)
}