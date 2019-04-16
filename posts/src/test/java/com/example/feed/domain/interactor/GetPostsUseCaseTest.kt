package com.example.feed.domain.interactor

import com.example.feed.domain.model.Post
import com.example.feed.domain.repository.PostsRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetPostsUseCaseTest {
  
  private val testError = RuntimeException("test error")
  
  @Mock
  lateinit var postsRepository: PostsRepository

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
  }
  
  @Test
  fun get_posts_success() {
    val list = emptyList<Post>()
    whenever(postsRepository.getPosts()).thenReturn(Single.just(list))
    
    val useCase = GetPostsUseCase(postsRepository)
    
    val testObserver = useCase.execute().test()
    testObserver.awaitTerminalEvent()
    
    verify(postsRepository).getPosts()
    
    testObserver
      .assertNoErrors()
      .assertValueCount(1)
      .assertValue(list)
  }
  
  @Test
  fun get_posts_failure() {
    whenever(postsRepository.getPosts()).thenReturn(Single.error(testError))
    
    val useCase = GetPostsUseCase(postsRepository)
    
    val testObserver = useCase.execute().test()
    testObserver.awaitTerminalEvent()
    
    verify(postsRepository).getPosts()
    
    testObserver
      .assertError(testError)
      .assertNoValues()
  }
}