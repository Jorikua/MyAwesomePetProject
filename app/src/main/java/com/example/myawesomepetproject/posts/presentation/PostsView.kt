package com.example.myawesomepetproject.posts.presentation

import com.arellomobile.mvp.MvpView
import com.example.myawesomepetproject.posts.presentation.model.PostViewModel

interface PostsView: MvpView {
  fun onLoadPostsSuccess(list: List<PostViewModel>)
  fun onLoadPostsFailure()
}