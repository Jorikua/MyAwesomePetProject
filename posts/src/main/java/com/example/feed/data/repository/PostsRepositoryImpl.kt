package com.example.feed.data.repository

import com.example.base.di.scope.PerFeature
import com.example.feed.data.mapper.PostsMapper
import com.example.feed.data.net.PostsApi
import com.example.feed.domain.model.Post
import com.example.feed.domain.repository.PostsRepository
import io.reactivex.Single
import javax.inject.Inject

@PerFeature
class PostsRepositoryImpl @Inject constructor(
    private val postsApi: PostsApi,
    private val postsMapper: PostsMapper) : PostsRepository {

    override fun getPosts(): Single<List<Post>> {
        return postsApi.getPosts()
            .map { postsMapper.map(it) }
    }
}