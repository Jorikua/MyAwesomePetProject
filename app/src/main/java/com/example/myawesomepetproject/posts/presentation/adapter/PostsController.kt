package com.example.myawesomepetproject.posts.presentation.adapter

import com.airbnb.epoxy.TypedEpoxyController
import com.example.base.di.scope.PerFragment
import com.example.myawesomepetproject.posts.presentation.adapter.model.postEpoxyView
import com.example.myawesomepetproject.posts.presentation.model.PostViewModel
import javax.inject.Inject

@PerFragment
class PostsController @Inject constructor() : TypedEpoxyController<List<PostViewModel>>() {
  
  var postsListener: PostsListener? = null
  
  override fun buildModels(data: List<PostViewModel>) {
    data.forEach {
      addPost(it)
    }
  }
  
  private fun addPost(postViewModel: PostViewModel) {
    postEpoxyView {
      id(postViewModel.id)
      postViewModel(postViewModel)
      postClick { postsListener?.onPostClick(postViewModel) }
    }
  }
  
  interface PostsListener {
    fun onPostClick(postViewModel: PostViewModel)
  }
}