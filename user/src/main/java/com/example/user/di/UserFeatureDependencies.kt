package com.example.user.di

import retrofit2.Retrofit

interface UserFeatureDependencies {
  fun retrofit(): Retrofit
}