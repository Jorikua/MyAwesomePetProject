package com.example.user.di

import com.example.user.domain.repository.UserRepository

interface UserFeatureComponentApi {
  fun repository(): UserRepository
}