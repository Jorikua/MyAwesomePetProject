package com.example.feed.data.repository

import com.example.base.di.scope.PerFeature
import com.example.feed.data.mapper.CommentMapper
import com.example.feed.data.mapper.PostMapper
import com.example.feed.data.net.PostsApi
import com.example.feed.domain.model.Comment
import com.example.feed.domain.model.Post
import com.example.feed.domain.repository.PostsRepository
import io.reactivex.Single
import javax.inject.Inject

@PerFeature
class PostsRepositoryImpl @Inject constructor(
    private val postsApi: PostsApi,
    private val postMapper: PostMapper,
    private val commentMapper: CommentMapper) : PostsRepository {
  override fun getPost(postId: Int): Single<Post> {
    return postsApi.getPost(postId)
        .map { postMapper.map(it) }
  }
  
  override fun getCommentsByPostId(postId: Int): Single<List<Comment>> {
    return postsApi.getComments(postId)
        .map { commentMapper.map(it) }
  }
  
  override fun getPosts(): Single<List<Post>> {
    return postsApi.getPosts()
        .map { postMapper.map(it) }
  }
}