package com.example.user.data.net

import com.example.user.data.model.UserApiModel
import io.reactivex.Single

interface UserApi {
  fun getUser(userId: Int): Single<UserApiModel>
}