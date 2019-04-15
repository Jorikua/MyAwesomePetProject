package com.example.feed.di

import com.example.base.di.scope.PerFeature
import com.example.feed.data.net.PostsApi
import com.example.feed.data.net.PostsApiImpl
import com.example.feed.data.net.PostsService
import com.example.feed.data.repository.PostsRepositoryImpl
import com.example.feed.domain.repository.PostsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class PostsDataModule {
  
  @Binds
  @PerFeature
  abstract fun provideRepository(impl: PostsRepositoryImpl): PostsRepository
  
  @Binds
  @PerFeature
  abstract fun provideApi(impl: PostsApiImpl): PostsApi
  
  @Module
  companion object {
    
    @JvmStatic
    @PerFeature
    @Provides
    fun provideService(retrofit: Retrofit): PostsService {
      return retrofit.create(PostsService::class.java)
    }
  }
}