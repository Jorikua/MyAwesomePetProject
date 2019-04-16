package com.example.feed.domain.interactor

import com.example.feed.domain.model.Comment
import com.example.feed.domain.model.Post
import com.example.feed.domain.repository.PostsRepository
import com.example.user.domain.model.User
import com.example.user.domain.repository.UserRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetFullPostUseCaseTest {
  
  private val testError = RuntimeException("Test error")
  
  @Mock
  lateinit var userRepository: UserRepository
  
  @Mock
  lateinit var postsRepository: PostsRepository
  
  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)
  }
  
  @Test
  fun get_full_post_success() {
    val userId = 0
    val postId = 0
    val comments = listOf(Comment(1, postId, "", "", ""), Comment(2, postId, "", "", ""))
    val post = Post(postId, userId, "", "")
    val user = User(userId, "", "", "")
    whenever(userRepository.getUser(userId)).thenReturn(Single.just(user))
    whenever(postsRepository.getCommentsByPostId(postId)).thenReturn(Single.just(comments))
    whenever(postsRepository.getPost(postId)).thenReturn(Single.just(post))
    
    val useCase = GetFullPostUseCase(userRepository, postsRepository)
    
    val params = GetFullPostUseCase.Params(userId, postId)
    val testObserver = useCase.execute(params).test()
    testObserver.awaitTerminalEvent()
    
    val fullpost = testObserver.values().first()
    
    verify(userRepository).getUser(userId)
    verify(postsRepository).getCommentsByPostId(postId)
    verify(postsRepository).getPost(postId)
    
    testObserver.assertNoErrors()
      .assertValueCount(1)
      .assertValue(fullpost)
  }
  
  @Test
  fun get_full_post_failure() {
    val userId = 0
    val postId = 0
    val comments = listOf(Comment(1, postId, "", "", ""), Comment(2, postId, "", "", ""))
    whenever(userRepository.getUser(userId)).thenReturn(Single.error(testError))
    whenever(postsRepository.getCommentsByPostId(postId)).thenReturn(Single.just(comments))
    whenever(postsRepository.getPost(postId)).thenReturn(Single.just(Post(postId, userId, "", "")))
  
    val useCase = GetFullPostUseCase(userRepository, postsRepository)
  
    val params = GetFullPostUseCase.Params(userId, postId)
    val testObserver = useCase.execute(params).test()
    testObserver.awaitTerminalEvent()
  
    verify(userRepository).getUser(userId)
    verify(postsRepository).getPost(postId)
    verify(postsRepository).getCommentsByPostId(postId)
  
    testObserver.assertError(testError)
      .assertNoValues()
  }
}