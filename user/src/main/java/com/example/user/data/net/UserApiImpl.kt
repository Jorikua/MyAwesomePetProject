package com.example.user.data.net

import com.example.base.di.scope.PerFeature
import com.example.user.data.model.UserApiModel
import io.reactivex.Single
import javax.inject.Inject

@PerFeature
class UserApiImpl @Inject constructor(private val userService: UserService): UserApi {
  override fun getUser(userId: Int): Single<UserApiModel> {
    return userService.getUser(userId)
  }
}