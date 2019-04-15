package com.example.myawesomepetproject.posts.di

import com.arellomobile.mvp.MvpPresenter
import com.example.myawesomepetproject.di.scope.PerFragment
import com.example.myawesomepetproject.posts.data.net.PostsApi
import com.example.myawesomepetproject.posts.data.net.PostsApiImpl
import com.example.myawesomepetproject.posts.data.net.PostsService
import com.example.myawesomepetproject.posts.data.repository.PostsRepositoryImpl
import com.example.myawesomepetproject.posts.domain.repository.PostsRepository
import com.example.myawesomepetproject.posts.presentation.PostsPresenter
import com.example.myawesomepetproject.posts.presentation.PostsView
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class PostsModule {
  
  @Binds
  @PerFragment
  abstract fun providePresenter(impl: PostsPresenter): MvpPresenter<PostsView>
  
  @Binds
  @PerFragment
  abstract fun provideRepository(impl: PostsRepositoryImpl): PostsRepository
  
  @Binds
  abstract fun provideApi(impl: PostsApiImpl): PostsApi
  
  @Module
  companion object {
    
    @JvmStatic
    @PerFragment
    @Provides
    fun provideService(retrofit: Retrofit): PostsService {
      return retrofit.create(PostsService::class.java)
    }
  }
}