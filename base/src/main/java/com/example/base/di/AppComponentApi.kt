package com.example.base.di

import retrofit2.Retrofit

interface AppComponentApi {
  fun retrofit(): Retrofit
}