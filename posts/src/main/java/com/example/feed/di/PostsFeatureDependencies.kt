package com.example.feed.di

import retrofit2.Retrofit

interface PostsFeatureDependencies {
  fun retrofit(): Retrofit
}