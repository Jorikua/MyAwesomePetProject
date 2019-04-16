package com.example.user.domain.repository

import com.example.user.domain.model.User
import io.reactivex.Single

interface UserRepository {
  fun getUser(userId: Int): Single<User>
}