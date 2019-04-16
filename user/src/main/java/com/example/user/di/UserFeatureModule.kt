package com.example.user.di

import com.example.base.di.scope.PerFeature
import com.example.user.data.net.UserApi
import com.example.user.data.net.UserApiImpl
import com.example.user.data.net.UserService
import com.example.user.data.repository.UserRepositoryImpl
import com.example.user.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class UserFeatureModule {
  
  @Binds
  @PerFeature
  abstract fun provideRepository(impl: UserRepositoryImpl): UserRepository
  
  @Binds
  @PerFeature
  abstract fun provideApi(impl: UserApiImpl): UserApi
  
  @Module
  companion object {
    @JvmStatic
    @PerFeature
    @Provides
    fun provideService(retrofit: Retrofit): UserService {
      return retrofit.create(UserService::class.java)
    }
  }
  
}