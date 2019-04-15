package com.example.feed.di

import retrofit2.Retrofit

interface PostsDependencies {
  fun retrofit(): Retrofit
}