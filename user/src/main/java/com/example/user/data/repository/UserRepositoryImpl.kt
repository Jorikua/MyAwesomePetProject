package com.example.user.data.repository

import com.example.base.di.scope.PerFeature
import com.example.user.data.mapper.UserMapper
import com.example.user.data.net.UserApi
import com.example.user.domain.model.User
import com.example.user.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

@PerFeature
class UserRepositoryImpl @Inject constructor(private val userApi: UserApi,
    private val userMapper: UserMapper) : UserRepository {
  override fun getUser(userId: Int): Single<User> {
    return userApi.getUser(userId)
        .map { userMapper.map(it) }
  }
}