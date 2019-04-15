package com.example.feed.di

import com.example.feed.domain.repository.PostsRepository

interface PostsFeatureComponentApi {
  fun postsRepository(): PostsRepository
}