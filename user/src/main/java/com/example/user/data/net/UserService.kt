package com.example.user.data.net

import com.example.user.data.model.UserApiModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
  @GET("users/{id}")
  fun getUser(@Path("id") userId: Int): Single<UserApiModel>
}