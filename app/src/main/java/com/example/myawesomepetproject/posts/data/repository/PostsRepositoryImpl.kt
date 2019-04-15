package com.example.myawesomepetproject.posts.data.repository

import com.example.myawesomepetproject.di.scope.PerFragment
import com.example.myawesomepetproject.posts.data.mapper.PostsMapper
import com.example.myawesomepetproject.posts.data.net.PostsApi
import com.example.myawesomepetproject.posts.domain.model.Post
import com.example.myawesomepetproject.posts.domain.repository.PostsRepository
import io.reactivex.Single
import javax.inject.Inject

@PerFragment
class PostsRepositoryImpl @Inject constructor(private val postsApi: PostsApi,
    private val postsMapper: PostsMapper): PostsRepository {
  override fun getPosts(): Single<List<Post>> {
    return postsApi.getPosts()
        .map { postsMapper.map(it) }
  }
}