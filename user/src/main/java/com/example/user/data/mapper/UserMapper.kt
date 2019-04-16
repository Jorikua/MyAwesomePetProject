package com.example.user.data.mapper

import com.example.base.di.scope.PerFeature
import com.example.base.mapper.Mapper
import com.example.user.data.model.UserApiModel
import com.example.user.domain.model.User
import javax.inject.Inject

@PerFeature
class UserMapper @Inject constructor() : Mapper<UserApiModel, User>() {
  override fun reverse(to: User): UserApiModel {
    TODO("not needed")
  }
  
  override fun map(from: UserApiModel): User {
    return with(from) {
      User(id, name, username, website)
    }
  }
}